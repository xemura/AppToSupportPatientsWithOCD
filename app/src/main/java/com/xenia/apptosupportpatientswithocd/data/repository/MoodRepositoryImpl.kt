package com.xenia.apptosupportpatientswithocd.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.xenia.apptosupportpatientswithocd.data.entity.MoodEntity
import com.xenia.apptosupportpatientswithocd.domain.entity.MoodModel
import com.xenia.apptosupportpatientswithocd.domain.repository.MoodRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class MoodRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val fireStoreDatabase: FirebaseFirestore
): MoodRepository {
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private val currentUserUID = firebaseAuth.currentUser?.uid
    private var moodsList: MutableList<MoodModel> = mutableListOf()

    private val moods = flow {
        val userDeferred = coroutineScope.async {
            val value = fireStoreDatabase.collection("$currentUserUID")
                .document("moods")
                .collection("moodsList")
                .get()
                .await()

            for (i in value.documents) {
                if (value.documents.size > moodsList.size) {
                    val mood = MoodModel(
                        id = i.id,
                        time = i.data?.getValue("data").toString(),
                        assessment = i.data?.getValue("moodAssessment").toString().toInt(),
                        note = i.data?.getValue("moodNote").toString()
                    )

                    moodsList.add(mood)
                }
            }
            Log.d("TAG", moodsList.size.toString())

            moodsList
        }

        emit(userDeferred.await())
    }
    override fun saveMood(assessment: Int, note: String) {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val date = Date()
        val current = formatter.format(date)

        fireStoreDatabase.collection("$currentUserUID")
            .document("moods")
            .collection("moodsList")
            .document("userInfo")
            .set(MoodEntity(current, assessment, note))
            .addOnSuccessListener {
                Log.d("TAG", "SUCCESS")
            }
            .addOnFailureListener {
                Log.d("TAG", "FAIL")
            }
    }

    override fun getMoods(): Flow<List<MoodModel>> = moods

    override fun getMoodByID(id: String): Flow<MoodModel> {
        TODO("Not yet implemented")
    }

    override fun updateMoodByID(id: String, assessment: Int, note: String) {
        TODO("Not yet implemented")
    }
}