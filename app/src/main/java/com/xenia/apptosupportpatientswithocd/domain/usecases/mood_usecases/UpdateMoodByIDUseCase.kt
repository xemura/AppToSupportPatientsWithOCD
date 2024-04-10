package com.xenia.apptosupportpatientswithocd.domain.usecases.mood_usecases

import com.xenia.apptosupportpatientswithocd.domain.repository.MoodRepository
import javax.inject.Inject

class UpdateMoodByIDUseCase @Inject constructor(
    private val repository: MoodRepository
) {
    operator fun invoke(id: String, assessment: Int, note: String) {
        return repository.updateMoodByID(id, assessment, note)
    }
}