package com.xenia.apptosupportpatientswithocd.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.xenia.apptosupportpatientswithocd.domain.entity.Action
import com.xenia.apptosupportpatientswithocd.domain.entity.ScriptModel
import com.xenia.apptosupportpatientswithocd.domain.repository.ScriptsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ScriptsRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val fireStoreDatabase: FirebaseFirestore
) : ScriptsRepository {

    private val currentUserUID = firebaseAuth.currentUser?.uid
    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    override fun getScriptsList(): Flow<List<ScriptModel>?> = callbackFlow {
        val listener = fireStoreDatabase.collection("$currentUserUID")
            .document("scripts")
            .collection("scriptsList")
            .addSnapshotListener { data, e ->
                if (e != null) {
                    close(e)
                }

                if (data != null) {

                    coroutineScope.launch {
                        val scriptsList: MutableList<ScriptModel> = mutableListOf()

                        for (i in data) {
                            Log.d("TAG", "here now script 1")

                            val actions = async { getActions(i.id) }.await()

                            Log.d("TAG", "actions in script = $actions")

                            val script = ScriptModel(
                                name = i.data.getValue("name").toString(),
                                dropDownBoxEnabled = i.data.getValue("dropDownBoxEnabled")
                                    .toString().toBoolean(),
                                listActions = actions
                            )

                            Log.d("TAG", script.toString())

                            scriptsList.add(script)

                        }

                        trySend(scriptsList)

                        Log.d("TAG", "here now script 2")
                    }
                }
            }

        awaitClose {
            listener.remove()
        }
    }

    private suspend fun getActions(idScript: String): List<Action> {
        Log.d("TAG", "here 1")
        val list = coroutineScope.async {
            val actionsList: MutableList<Action> = mutableListOf()

            val value = fireStoreDatabase.collection("$currentUserUID")
                .document("scripts")
                .collection("actionsList")
                .whereEqualTo("scriptID", idScript)
                .get()
                .await()


            if (value != null) {
                for (i in value.documents) {
                    val action = Action(
                        actionText = i.data?.getValue("actionText").toString(),
                        checkBoxState = i.data?.getValue("checkBoxState").toString()
                            .toBoolean()
                    )

                    Log.d("TAG", "action = $action")
                    actionsList.add(action)
                }
            }

            Log.d("TAG", "here 2")
            Log.d("TAG", actionsList.toString())
            actionsList
        }
        return list.await()
    }
}