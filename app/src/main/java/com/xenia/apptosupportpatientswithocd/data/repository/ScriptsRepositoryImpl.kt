package com.xenia.apptosupportpatientswithocd.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.xenia.apptosupportpatientswithocd.data.entity.ActionEntity
import com.xenia.apptosupportpatientswithocd.data.entity.ScriptEntity
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
                            val actions = async { getActions(i.id) }.await()

                            val script = ScriptModel(
                                id = i.id,
                                name = i.data.getValue("name").toString(),
                                dropDownBoxEnabled = i.data.getValue("dropDownBoxEnabled")
                                    .toString().toBoolean(),
                                listActions = actions
                            )

                            scriptsList.add(script)

                        }
                        trySend(scriptsList)
                    }
                }
            }

        awaitClose {
            listener.remove()
        }
    }

    private suspend fun getActions(idScript: String): List<Action> {
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
                        id = i.id,
                        actionText = i.data?.getValue("actionText").toString(),
                        checkBoxState = i.data?.getValue("checkBoxState").toString()
                            .toBoolean()
                    )

                    actionsList.add(action)
                }
            }

            actionsList
        }
        return list.await()
    }

    override fun changeDropDownBoxState(
        idScript: String,
        name: String,
        dropDownBoxEnabled: Boolean
    ) {
        coroutineScope.launch {
            fireStoreDatabase.collection("$currentUserUID")
                .document("scripts")
                .collection("scriptsList")
                .document(idScript)
                .set(ScriptEntity(name, dropDownBoxEnabled))
                .await()

            Log.d("TAG", "changeDropDownBoxState SUCCESS")
        }

    }

    override fun changeCheckBoxState(
        idAction: String,
        actionText: String,
        checkBoxState: Boolean,
        scriptID: String
    ) {
        Log.d("TAG", "$idAction, $actionText, $checkBoxState, $scriptID")
        fireStoreDatabase.collection("$currentUserUID")
            .document("scripts")
            .collection("actionsList")
            .document(idAction)
            .set(ActionEntity(actionText, checkBoxState, scriptID))
            .addOnSuccessListener {
                Log.d("TAG", "changeCheckBoxState SUCCESS")
            }
            .addOnFailureListener {
                Log.d("TAG", "changeCheckBoxState FAIL")
            }
    }

    override fun addScript(scriptName: String, listActions: List<Action>) {
        coroutineScope.launch {
            val scriptEntity = ScriptEntity(scriptName, false)

            val script = fireStoreDatabase.collection("$currentUserUID")
                .document("scripts")
                .collection("scriptsList")
                .add(scriptEntity)
                .await()
                .get()
                .await()


            listActions.forEach { action ->
                coroutineScope.launch {
                    fireStoreDatabase.collection("$currentUserUID")
                        .document("scripts")
                        .collection("actionsList")
                        .add(
                            ActionEntity(
                                action.actionText,
                                false,
                                script.id
                            )
                        )
                        .await()
                }
            }
        }
    }

    override fun deleteScript(script: ScriptModel) {
        coroutineScope.launch {
            val scriptID = script.id
            val actionsList = script.listActions

            actionsList?.forEach { action ->
                fireStoreDatabase.collection("$currentUserUID")
                    .document("scripts")
                    .collection("actionsList")
                    .document(action.id)
                    .delete()
                    .await()
            }

            fireStoreDatabase.collection("$currentUserUID")
                .document("scripts")
                .collection("scriptsList")
                .document(scriptID)
                .delete()
                .await()

        }
    }
}