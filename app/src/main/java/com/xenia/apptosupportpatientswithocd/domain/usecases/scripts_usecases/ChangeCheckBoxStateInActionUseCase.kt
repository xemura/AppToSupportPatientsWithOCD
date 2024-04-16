package com.xenia.apptosupportpatientswithocd.domain.usecases.scripts_usecases

import com.xenia.apptosupportpatientswithocd.domain.repository.ScriptsRepository
import javax.inject.Inject

class ChangeCheckBoxStateInActionUseCase @Inject constructor(
    private val repository: ScriptsRepository
) {
    operator fun invoke(
        idAction: String, actionText: String,
        checkBoxState: Boolean, scriptID: String
    ) {
        repository.changeCheckBoxState(idAction, actionText, checkBoxState, scriptID)
    }
}