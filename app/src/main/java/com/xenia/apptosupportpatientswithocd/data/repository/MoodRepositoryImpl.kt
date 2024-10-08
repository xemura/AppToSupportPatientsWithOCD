package com.xenia.apptosupportpatientswithocd.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.xenia.apptosupportpatientswithocd.data.entity.MoodEntity
import com.xenia.apptosupportpatientswithocd.domain.entity.MoodModel
import com.xenia.apptosupportpatientswithocd.domain.repository.MoodRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject


class MoodRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val fireStoreDatabase: FirebaseFirestore
) : MoodRepository {
    private val currentUserUID = firebaseAuth.currentUser?.uid

    private val moodDocRef = fireStoreDatabase.collection("$currentUserUID")
        .document("moods")
        .collection("moodsList")


    override fun saveMood(assessment: Int, note: String) {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val date = Date()
        val current = formatter.format(date)

        moodDocRef.add(MoodEntity(current, assessment, note))
            .addOnSuccessListener {
                Log.d("TAG", "saveMood SUCCESS")
            }
            .addOnFailureListener {
                Log.d("TAG", "saveMood FAIL")
            }
    }

    override fun getMoods(): Flow<List<MoodModel>?> = callbackFlow {
        val listener = moodDocRef
            .orderBy("time", Query.Direction.DESCENDING)
            .addSnapshotListener { data, e ->
                if (e != null) {
                    close(e)
                }

                if (data != null) {

                    val moodsList: MutableList<MoodModel> = mutableListOf()

                    for (i in data) {
                        val mood = MoodModel(
                            id = i.id,
                            time = i.data.getValue("time").toString(),
                            assessment = i.data.getValue("assessment").toString().toInt(),
                            note = i.data.getValue("note").toString()
                        )

                        moodsList.add(mood)
                    }
                    trySend(moodsList)
                }
            }

        awaitClose {
            listener.remove()
        }
    }

    override fun updateMoodByID(id: String, assessment: Int, note: String) {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val date = Date()
        val current = formatter.format(date)

        moodDocRef.document(id)
            .set(MoodEntity(current, assessment, note))
            .addOnSuccessListener {
                Log.d("TAG", "updateMoodByID SUCCESS")
            }
            .addOnFailureListener {
                Log.d("TAG", "updateMoodByID FAIL")
            }
    }

    override fun deleteMoodByID(id: String) {
        moodDocRef
            .document(id)
            .delete()
            .addOnSuccessListener {
                Log.d("TAG", "DocumentSnapshot successfully deleted!")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error deleting document", e)
            }
    }
}