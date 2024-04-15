package com.xenia.apptosupportpatientswithocd.domain.usecases.homework_usecases

import com.xenia.apptosupportpatientswithocd.domain.repository.HomeworkRepository
import com.xenia.apptosupportpatientswithocd.domain.entity.StatisticModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStatisticHomeworkByIDUseCase @Inject constructor(
    private val repository: HomeworkRepository
) {
    operator fun invoke(id: String): Flow<List<StatisticModel>?> {
        return repository.getStatisticHomeworkByID(id)
    }
}