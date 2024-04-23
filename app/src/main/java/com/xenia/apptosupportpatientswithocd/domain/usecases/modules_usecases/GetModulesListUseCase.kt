package com.xenia.apptosupportpatientswithocd.domain.usecases.modules_usecases

import com.xenia.apptosupportpatientswithocd.domain.entity.ModuleModel
import com.xenia.apptosupportpatientswithocd.domain.repository.ModulesRepository
import javax.inject.Inject

class GetModulesListUseCase @Inject constructor(
    private val repository: ModulesRepository
) {
    operator fun invoke(): List<ModuleModel> {
        return repository.getModulesList()
    }
}