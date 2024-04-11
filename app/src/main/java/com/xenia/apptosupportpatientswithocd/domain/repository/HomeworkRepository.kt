package com.xenia.apptosupportpatientswithocd.domain.repository

import com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.homework_screens.HomeworkModel

interface HomeworkRepository {
    fun addHomework(
        obsessionInfo: String,
        triggerInfo: String,
        adviceInfo: String,
    )

    fun getHomeworks(): List<HomeworkModel>

    fun deleteHomework(id: String)

    fun updateHomework(
        obsessionInfo: String,
        triggerInfo: String,
        adviceInfo: String,
    )
}