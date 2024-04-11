package com.xenia.apptosupportpatientswithocd.domain.usecases.profile_usecases

import com.xenia.apptosupportpatientswithocd.domain.repository.ProfileRepository
import javax.inject.Inject

class UpdateUserInfoUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    fun updateUserInfo(name: String, notificationEnable: Boolean, notificationTime: String) {
        return repository.updateUserInfo(name, notificationEnable, notificationTime)
    }
}