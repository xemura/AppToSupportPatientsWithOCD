package com.xenia.apptosupportpatientswithocd.domain.usecases.mood_usecases

import com.xenia.apptosupportpatientswithocd.domain.repository.MoodRepository
import javax.inject.Inject


class DeleteMoodUseCase @Inject constructor(
    private val repository: MoodRepository
) {
    operator fun invoke(id: String) {
        return repository.deleteMoodByID(id)
    }
}