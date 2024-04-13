package com.xenia.apptosupportpatientswithocd.domain.usecases.homework_usecases

import com.xenia.apptosupportpatientswithocd.domain.repository.HomeworkRepository
import javax.inject.Inject

class AddHomeworkUseCase @Inject constructor(
    private val repository: HomeworkRepository
) {
    operator fun invoke(
        obsessionInfo: String,
        triggerInfo: String,
        adviceInfo: String
    ) {
        repository.addHomework(obsessionInfo, triggerInfo, adviceInfo)
    }
}