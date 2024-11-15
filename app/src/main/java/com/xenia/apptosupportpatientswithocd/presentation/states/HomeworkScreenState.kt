package com.xenia.apptosupportpatientswithocd.presentation.states

import com.xenia.apptosupportpatientswithocd.domain.entity.HomeworkModel

sealed class HomeworkScreenState {
    data object Initial: HomeworkScreenState()
    data object Loading: HomeworkScreenState()
    data class HomeworkMain(
        val homeworksList: List<HomeworkModel>?
    ) : HomeworkScreenState()
}