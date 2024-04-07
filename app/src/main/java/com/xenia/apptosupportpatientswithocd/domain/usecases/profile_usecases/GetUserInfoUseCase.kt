package com.xenia.apptosupportpatientswithocd.domain.usecases.profile_usecases

import com.xenia.apptosupportpatientswithocd.domain.entity.UserModel
import com.xenia.apptosupportpatientswithocd.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke(): Flow<UserModel> {
        return repository.getUserInfo()
    }
}