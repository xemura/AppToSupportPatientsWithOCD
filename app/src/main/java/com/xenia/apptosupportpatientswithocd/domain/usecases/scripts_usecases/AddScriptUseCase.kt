package com.xenia.apptosupportpatientswithocd.domain.usecases.scripts_usecases

import com.xenia.apptosupportpatientswithocd.domain.entity.Action
import com.xenia.apptosupportpatientswithocd.domain.repository.ScriptsRepository
import javax.inject.Inject

class AddScriptUseCase @Inject constructor(
    private val repository: ScriptsRepository
) {
    operator fun invoke(
        nameScript: String, listActions: List<Action>,
    ) {
        repository.addScript(nameScript, listActions)
    }
}