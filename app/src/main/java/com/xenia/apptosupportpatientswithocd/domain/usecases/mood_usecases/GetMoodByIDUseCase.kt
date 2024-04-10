package com.xenia.apptosupportpatientswithocd.domain.usecases.mood_usecases

import com.xenia.apptosupportpatientswithocd.domain.entity.MoodModel
import com.xenia.apptosupportpatientswithocd.domain.repository.MoodRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoodByIDUseCase @Inject constructor(
    private val repository: MoodRepository
) {
    operator fun invoke(id: String): Flow<MoodModel> {
        return repository.getMoodByID(id)
    }
}