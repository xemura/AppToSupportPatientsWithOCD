package com.xenia.apptosupportpatientswithocd.presentation.states

import com.xenia.apptosupportpatientswithocd.domain.entity.ScriptModel


sealed class ScriptsScreenState {
    data object Initial : ScriptsScreenState()
    data object Loading : ScriptsScreenState()
    data class Scripts(
        val scriptsList: List<ScriptModel>?
    ) : ScriptsScreenState()
}