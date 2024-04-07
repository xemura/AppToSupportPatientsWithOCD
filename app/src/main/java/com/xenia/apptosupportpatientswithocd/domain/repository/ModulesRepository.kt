package com.xenia.apptosupportpatientswithocd.domain.repository

import com.xenia.apptosupportpatientswithocd.domain.entity.ModuleContentModel
import com.xenia.apptosupportpatientswithocd.domain.entity.ModuleModel
import kotlinx.coroutines.flow.Flow

interface ModulesRepository {
    fun getModulesList(): Flow<List<ModuleModel>>
}