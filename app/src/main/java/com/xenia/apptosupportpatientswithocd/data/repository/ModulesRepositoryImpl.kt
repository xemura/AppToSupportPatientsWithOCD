package com.xenia.apptosupportpatientswithocd.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.xenia.apptosupportpatientswithocd.R
import com.xenia.apptosupportpatientswithocd.domain.entity.ModuleContentModel
import com.xenia.apptosupportpatientswithocd.domain.entity.ModuleModel
import com.xenia.apptosupportpatientswithocd.domain.repository.ModulesRepository
import com.xenia.apptosupportpatientswithocd.presentation.TAG
import com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.homework_screens.HomeworkModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ModulesRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val fireStoreDatabase: FirebaseFirestore
) : ModulesRepository {

    private val modulesList = mutableListOf<ModuleModel>()
    private val moduleContentList = mutableListOf<ModuleContentModel>()

    private val scope = CoroutineScope(Dispatchers.Default)

    private val currentUserUID = firebaseAuth.currentUser?.uid

    private val modulesListFlow = flow {
        fireStoreDatabase.collection("$currentUserUID")
            .document("modules")
            .collection("modulesList")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    scope.launch {
                        val contentList = scope.async {
                            getModuleContent(document.id)
                        }.await()

                        Log.d("TAG", "${document.id} => жопа")

                        val moduleItem = ModuleModel(
                            document.id,
                            document.data.getValue("name").toString(),
                            R.drawable.anxiety,
                            contentList
                        )
                        modulesList.add(moduleItem)
                        Log.d("TAG", "${document.id} => $moduleItem")
                        Log.d("TAG", "${document.id} => ${document.data.getValue("name")}")
                    }


                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }

        emit(modulesList)
    }
    override fun getModulesList(): Flow<List<ModuleModel>> = modulesListFlow

    private suspend fun getModuleContent(moduleID: String): List<ModuleContentModel> {
        val content = scope.async {
            val x = fireStoreDatabase.collection("$currentUserUID")
                .document("modules")
                .collection("modulesList")
                .document(moduleID)
                .collection("articlesList")
                .get()
                .await()

            for (document in x) {
                val moduleContentItem = ModuleContentModel(
                    document.id,
                    document.data.getValue("name").toString(),
                    document.data.getValue("text").toString(),
                )
                moduleContentList.add(moduleContentItem)
                Log.d("TAG", "${document.id} => getModuleContent")
            }

            moduleContentList
        }

        return content.await()
    }
}