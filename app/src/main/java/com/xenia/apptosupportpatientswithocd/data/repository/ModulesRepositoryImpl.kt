package com.xenia.apptosupportpatientswithocd.data.repository

import com.xenia.apptosupportpatientswithocd.data.modules_data.modulesList
import com.xenia.apptosupportpatientswithocd.domain.entity.ModuleModel
import com.xenia.apptosupportpatientswithocd.domain.repository.ModulesRepository
import javax.inject.Inject

class ModulesRepositoryImpl @Inject constructor() : ModulesRepository {
    override fun getModulesList(): List<ModuleModel> = modulesList
}