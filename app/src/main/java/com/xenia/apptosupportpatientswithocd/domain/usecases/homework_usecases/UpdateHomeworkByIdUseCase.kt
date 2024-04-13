package com.xenia.apptosupportpatientswithocd.domain.usecases.homework_usecases

import com.xenia.apptosupportpatientswithocd.domain.repository.HomeworkRepository
import javax.inject.Inject

class UpdateHomeworkByIdUseCase @Inject constructor(
    private val repository: HomeworkRepository
) {
    operator fun invoke(
        id: String,
        obsessionInfo: String,
        triggerInfo: String,
        adviceInfo: String
    ) {
        repository.updateHomeworkById(id, obsessionInfo, triggerInfo, adviceInfo)
    }
}