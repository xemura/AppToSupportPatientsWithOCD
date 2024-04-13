package com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.homework_screens

import com.xenia.apptosupportpatientswithocd.domain.entity.HomeworkModel

sealed class HomeworkScreenState {
    data object Initial: HomeworkScreenState()
    data object Loading: HomeworkScreenState()
    data class HomeworkMain(
        val homeworksList: List<HomeworkModel>?
    ) : HomeworkScreenState()
}