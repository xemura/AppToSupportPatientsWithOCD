package com.xenia.apptosupportpatientswithocd.domain.repository

import com.xenia.apptosupportpatientswithocd.domain.entity.ModuleModel

interface ModulesRepository {
    fun getModulesList(): List<ModuleModel>
}