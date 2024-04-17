package com.xenia.apptosupportpatientswithocd.domain.repository

import com.xenia.apptosupportpatientswithocd.domain.entity.Action
import com.xenia.apptosupportpatientswithocd.domain.entity.ScriptModel
import kotlinx.coroutines.flow.Flow

interface ScriptsRepository {
    fun getScriptsList(): Flow<List<ScriptModel>?>
    fun changeDropDownBoxState(idScript: String, name: String, dropDownBoxEnabled: Boolean)

    fun changeCheckBoxState(
        idAction: String, actionText: String,
        checkBoxState: Boolean, scriptID: String
    )

    fun addScript(
        scriptName: String,
        listActions: List<Action>
    )

    fun deleteScript(script: ScriptModel)
}