package com.xenia.apptosupportpatientswithocd.domain.repository

import com.xenia.apptosupportpatientswithocd.domain.entity.MoodModel
import kotlinx.coroutines.flow.Flow

interface MoodRepository {
    fun saveMood(assessment: Int, note: String)
    fun getMoods(): Flow<List<MoodModel>?>
    fun updateMoodByID(id: String, assessment: Int, note: String)
    fun deleteMoodByID(id: String)
}