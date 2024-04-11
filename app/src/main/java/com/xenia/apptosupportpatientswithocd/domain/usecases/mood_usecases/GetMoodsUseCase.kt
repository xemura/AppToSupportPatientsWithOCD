package com.xenia.apptosupportpatientswithocd.domain.usecases.mood_usecases

import com.xenia.apptosupportpatientswithocd.domain.entity.MoodModel
import com.xenia.apptosupportpatientswithocd.domain.repository.MoodRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoodsUseCase @Inject constructor(
    private val repository: MoodRepository
) {
    operator fun invoke(): Flow<List<MoodModel>?> {
        return repository.getMoods()
    }
}