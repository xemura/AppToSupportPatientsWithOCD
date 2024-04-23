package com.xenia.apptosupportpatientswithocd.domain.usecases.homework_usecases

import com.xenia.apptosupportpatientswithocd.domain.entity.StatisticModel
import com.xenia.apptosupportpatientswithocd.domain.repository.HomeworkRepository
import javax.inject.Inject

class SetStatisticHomeworkByIDUseCase @Inject constructor(
    private val repository: HomeworkRepository
) {
    operator fun invoke(statisticModel: StatisticModel) {
        repository.setStatisticHomeworkByID(statisticModel)
    }
}