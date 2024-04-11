package com.xenia.apptosupportpatientswithocd.domain.entity

import com.google.firebase.firestore.Exclude





data class MoodModel(
    val id: String = "",
    val time: String = "",
    val assessment: Int = 0,
    val note: String = ""
)