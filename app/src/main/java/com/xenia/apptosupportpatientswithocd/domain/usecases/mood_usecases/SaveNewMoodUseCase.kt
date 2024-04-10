package com.xenia.apptosupportpatientswithocd.domain.usecases.mood_usecases

import com.xenia.apptosupportpatientswithocd.domain.repository.MoodRepository
import javax.inject.Inject

class SaveNewMoodUseCase @Inject constructor(
    private val repository: MoodRepository
) {
    operator fun invoke(assessment: Int, note: String) {
        return repository.saveMood(assessment, note)
    }
}