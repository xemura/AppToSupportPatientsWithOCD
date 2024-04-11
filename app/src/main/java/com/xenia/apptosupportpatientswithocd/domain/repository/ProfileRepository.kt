package com.xenia.apptosupportpatientswithocd.domain.repository

import com.xenia.apptosupportpatientswithocd.domain.entity.UserModel
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getUserInfo(): Flow<UserModel>
    fun updateUserInfo(name: String, notificationEnable: Boolean, notificationTime: String)
}