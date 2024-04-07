package com.xenia.apptosupportpatientswithocd.domain.entity

data class UserModel(
    val name: String,
    val notificationEnable: Boolean,
    val notificationTime: String
)