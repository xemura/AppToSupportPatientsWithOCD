package com.xenia.apptosupportpatientswithocd.domain.usecases.scripts_usecases

import com.xenia.apptosupportpatientswithocd.domain.entity.HomeworkModel
import com.xenia.apptosupportpatientswithocd.domain.entity.ScriptModel
import com.xenia.apptosupportpatientswithocd.domain.repository.ScriptsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetScriptsUseCase @Inject constructor(
    private val repository: ScriptsRepository
) {
    operator fun invoke(): Flow<List<ScriptModel>?> {
        return repository.getScriptsList()
    }
}