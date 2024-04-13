package com.xenia.apptosupportpatientswithocd.domain.usecases.homework_usecases

import com.xenia.apptosupportpatientswithocd.domain.entity.HomeworkModel
import com.xenia.apptosupportpatientswithocd.domain.repository.HomeworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeworksUseCase @Inject constructor(
    private val repository: HomeworkRepository
) {
    operator fun invoke(): Flow<List<HomeworkModel>?> {
        return repository.getHomeworks()
    }
}