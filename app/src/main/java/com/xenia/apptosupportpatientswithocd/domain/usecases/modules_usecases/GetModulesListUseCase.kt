package com.xenia.apptosupportpatientswithocd.domain.usecases.modules_usecases

import com.xenia.apptosupportpatientswithocd.domain.entity.ModuleModel
import com.xenia.apptosupportpatientswithocd.domain.repository.ModulesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetModulesListUseCase @Inject constructor(
    private val repository: ModulesRepository
) {
    operator fun invoke(): Flow<List<ModuleModel>> {
        return repository.getModulesList()
    }
}