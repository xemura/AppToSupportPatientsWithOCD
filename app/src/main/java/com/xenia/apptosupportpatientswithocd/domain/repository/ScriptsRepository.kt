package com.xenia.apptosupportpatientswithocd.domain.repository

import com.xenia.apptosupportpatientswithocd.domain.entity.ScriptModel
import kotlinx.coroutines.flow.Flow

interface ScriptsRepository {
    fun getScriptsList(): Flow<List<ScriptModel>?>
}