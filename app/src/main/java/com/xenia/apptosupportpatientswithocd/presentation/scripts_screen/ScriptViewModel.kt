package com.xenia.apptosupportpatientswithocd.presentation.scripts_screen

import androidx.lifecycle.ViewModel
import com.xenia.apptosupportpatientswithocd.domain.usecases.scripts_usecases.GetScriptsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class ScriptViewModel @Inject constructor(
    private val getScriptsUseCase: GetScriptsUseCase
): ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private val scriptsFlow = getScriptsUseCase()

    val screenState = scriptsFlow
        .map { ScriptsScreenState.Scripts(scriptsList = it) as ScriptsScreenState }
        .onStart {
            emit(ScriptsScreenState.Loading)
        }.stateIn(
            scope = coroutineScope,
            started = SharingStarted.Lazily,
            initialValue = ScriptsScreenState.Initial
        )
}