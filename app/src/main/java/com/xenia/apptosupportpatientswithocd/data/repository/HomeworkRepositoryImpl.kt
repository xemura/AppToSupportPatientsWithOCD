package com.xenia.apptosupportpatientswithocd.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.xenia.apptosupportpatientswithocd.data.entity.HomeworkEntity
import com.xenia.apptosupportpatientswithocd.data.entity.StatisticEntity
import com.xenia.apptosupportpatientswithocd.domain.entity.HomeworkModel
import com.xenia.apptosupportpatientswithocd.domain.repository.HomeworkRepository
import com.xenia.apptosupportpatientswithocd.domain.entity.StatisticModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class HomeworkRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val fireStoreDatabase: FirebaseFirestore
): HomeworkRepository {

    private val currentUserUID = firebaseAuth.currentUser?.uid

    private val homeworkDocRef = fireStoreDatabase.collection("$currentUserUID")
        .document("homeworks")
        .collection("homeworksList")

    override fun addHomework(obsessionInfo: String, triggerInfo: String, adviceInfo: String) {

        homeworkDocRef.add(HomeworkEntity(obsessionInfo, triggerInfo, adviceInfo))
            .addOnSuccessListener {
                Log.d("TAG", "saveHomework SUCCESS")
            }
            .addOnFailureListener {
                Log.d("TAG", "saveHomework FAIL")
            }
    }

    override fun getHomeworks(): Flow<List<HomeworkModel>?> = callbackFlow {
        val listener = homeworkDocRef.addSnapshotListener { data, e ->
            if (e != null) {
                close(e)
            }

            if (data != null) {

                val homeworkList: MutableList<HomeworkModel> = mutableListOf()

                for (i in data) {

                    val homework = HomeworkModel(
                        id = i.id,
                        obsessionInfo = i.data.getValue("obsessionInfo").toString(),
                        triggerInfo = i.data.getValue("triggerInfo").toString(),
                        adviceInfo = i.data.getValue("adviceInfo").toString()
                    )

                    homeworkList.add(homework)
                }
                trySend(homeworkList)
            }
        }

        awaitClose {
            listener.remove()
        }
    }

    override fun deleteHomework(id: String) {
        homeworkDocRef
            .document(id)
            .delete()
            .addOnSuccessListener {
                Log.d("TAG", "DocumentSnapshot successfully deleted!")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error deleting document", e)
            }
    }

    override fun updateHomeworkById(id: String, obsessionInfo: String, triggerInfo: String, adviceInfo: String) {
        homeworkDocRef.document(id)
            .set(HomeworkEntity(obsessionInfo, triggerInfo, adviceInfo))
            .addOnSuccessListener {
                Log.d("TAG", "updateHomeworkById SUCCESS")
            }
            .addOnFailureListener {
                Log.d("TAG", "updateHomeworkById FAIL")
            }
    }

    override fun setStatisticHomeworkByID(statisticModel: StatisticModel) {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val date = Date()
        val current = formatter.format(date)

        val statisticEntity = StatisticEntity(
            statisticBeforePractice = statisticModel.statisticBeforePractice,
            statisticAfterPractice = statisticModel.statisticAfterPractice,
            homeworkID = statisticModel.homeworkID,
            time = current
        )

        fireStoreDatabase.collection("$currentUserUID")
            .document("homeworks")
            .collection("statisticHomeworksList")
            .add(statisticEntity)
            .addOnSuccessListener {
                Log.d("TAG", "setStatisticHomeworkByID SUCCESS")
            }
            .addOnFailureListener {
                Log.d("TAG", "setStatisticHomeworkByID FAIL")
            }
    }

    override fun getStatisticHomeworkByID(id: String): Flow<List<StatisticModel>?> = callbackFlow {
        Log.d("TAG", id)
        val listener = fireStoreDatabase
            .collection("$currentUserUID")
            .document("homeworks")
            .collection("statisticHomeworksList")
            .whereEqualTo("homeworkID", id)
            .limit(5)
            .orderBy("time", Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
                if (value != null) {
                    val list = mutableListOf<StatisticModel>()

                    for (i in value) {
                        Log.d("TAG", "here now")

                        val statistic = StatisticModel(
                            statisticBeforePractice = i.data.getValue("statisticBeforePractice").toString().toInt(),
                            statisticAfterPractice = i.data.getValue("statisticAfterPractice").toString().toInt(),
                            homeworkID = i.data.getValue("homeworkID").toString()
                        )

                        Log.d("TAG", "GET $statistic")

                        list.add(statistic)
                    }

                    trySend(list)
                }
            }

        awaitClose {
            listener.remove()
        }
    }
}