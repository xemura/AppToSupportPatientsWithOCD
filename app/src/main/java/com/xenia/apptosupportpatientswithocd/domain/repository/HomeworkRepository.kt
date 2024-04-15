package com.xenia.apptosupportpatientswithocd.domain.repository

import com.xenia.apptosupportpatientswithocd.domain.entity.HomeworkModel
import com.xenia.apptosupportpatientswithocd.domain.entity.StatisticModel
import kotlinx.coroutines.flow.Flow

interface HomeworkRepository {
    fun addHomework(
        obsessionInfo: String,
        triggerInfo: String,
        adviceInfo: String,
    )

    fun getHomeworks(): Flow<List<HomeworkModel>?>

    fun deleteHomework(id: String)

    fun updateHomeworkById(
        id: String,
        obsessionInfo: String,
        triggerInfo: String,
        adviceInfo: String,
    )

    fun setStatisticHomeworkByID(statisticModel: StatisticModel)

    fun getStatisticHomeworkByID(id: String): Flow<List<StatisticModel>?>
}