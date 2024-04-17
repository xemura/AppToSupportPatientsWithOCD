package com.xenia.apptosupportpatientswithocd.domain.usecases.scripts_usecases

import com.xenia.apptosupportpatientswithocd.domain.entity.Action
import com.xenia.apptosupportpatientswithocd.domain.entity.ScriptModel
import com.xenia.apptosupportpatientswithocd.domain.repository.ScriptsRepository
import javax.inject.Inject

class DeleteScriptUseCase @Inject constructor(
    private val repository: ScriptsRepository
) {
    operator fun invoke(script: ScriptModel) {
        repository.deleteScript(script)
    }
}