package com.xenia.apptosupportpatientswithocd.presentation.states

import com.xenia.apptosupportpatientswithocd.domain.entity.UserModel

sealed class ProfileScreenState {
    data object Initial: ProfileScreenState()
    data object Loading: ProfileScreenState()
    data class Profile(
        val userInfo: UserModel
    ) : ProfileScreenState()
}