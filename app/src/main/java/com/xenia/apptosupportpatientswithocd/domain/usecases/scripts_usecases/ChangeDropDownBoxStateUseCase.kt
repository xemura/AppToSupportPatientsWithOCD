package com.xenia.apptosupportpatientswithocd.domain.usecases.scripts_usecases

import com.xenia.apptosupportpatientswithocd.domain.repository.ScriptsRepository
import javax.inject.Inject

class ChangeDropDownBoxStateUseCase @Inject constructor(
    private val repository: ScriptsRepository
) {
    operator fun invoke(id: String, name: String, state: Boolean) {
        repository.changeDropDownBoxState(id, name, state)
    }
}