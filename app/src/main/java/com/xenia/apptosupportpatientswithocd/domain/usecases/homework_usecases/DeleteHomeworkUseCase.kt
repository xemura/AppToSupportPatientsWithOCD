package com.xenia.apptosupportpatientswithocd.domain.usecases.homework_usecases

import com.xenia.apptosupportpatientswithocd.domain.repository.HomeworkRepository
import javax.inject.Inject

class DeleteHomeworkUseCase @Inject constructor(
    private val repository: HomeworkRepository
) {
    operator fun invoke(id: String) {
        repository.deleteHomework(id)
    }
}