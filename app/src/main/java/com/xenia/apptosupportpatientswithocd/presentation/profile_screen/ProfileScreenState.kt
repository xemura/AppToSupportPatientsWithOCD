package com.xenia.apptosupportpatientswithocd.presentation.profile_screen

import com.google.firebase.auth.UserInfo
import com.xenia.apptosupportpatientswithocd.domain.entity.UserModel


sealed class ProfileScreenState {
    data object Initial: ProfileScreenState()
    data object Loading: ProfileScreenState()
    data class Profile(
        val userInfo: UserModel
    ) : ProfileScreenState()
}