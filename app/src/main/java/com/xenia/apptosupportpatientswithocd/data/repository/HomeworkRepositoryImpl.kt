package com.xenia.apptosupportpatientswithocd.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.xenia.apptosupportpatientswithocd.domain.repository.HomeworkRepository
import com.xenia.apptosupportpatientswithocd.presentation.TAG
import com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.homework_screens.HomeworkModel
import javax.inject.Inject

class HomeworkRepositoryImpl @Inject constructor(
    private val fireStoreDatabase: FirebaseFirestore
): HomeworkRepository {

    private val homeworksList = mutableListOf<HomeworkModel>()
    override fun addHomework(obsessionInfo: String, triggerInfo: String, adviceInfo: String) {
        val homework: MutableMap<String, Any> = HashMap()

        homework["obsessionInfo"] = obsessionInfo
        homework["triggerInfo"] = triggerInfo
        homework["adviceInfo"] = adviceInfo

        fireStoreDatabase.collection("homeworkInfo")
            .add(homework)
            .addOnSuccessListener {
                Log.d(TAG, "Added document with ID ${it.id}")
            }
            .addOnFailureListener {
                Log.w(TAG, "Error adding document $it")
            }
    }

    override fun getHomeworks(): List<HomeworkModel> {
        fireStoreDatabase.collection("homeworkInfo")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    // данные из документа надо преобразовать в данные модели
                    val homeworkItem = HomeworkModel(
                        document.id,
                        document.data.getValue("obsessionInfo").toString(),
                        document.data.getValue("triggerInfo").toString(),
                        document.data.getValue("adviceInfo").toString(),
                    )
                    homeworksList.add(homeworkItem)
                    Log.d(TAG, "${document.id} => ${document.data.getValue("obsessionInfo")}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        return homeworksList
    }

    override fun deleteHomework(id: String) {
        TODO("Not yet implemented")
    }

    override fun updateHomework(obsessionInfo: String, triggerInfo: String, adviceInfo: String) {
        TODO("Not yet implemented")
    }
}