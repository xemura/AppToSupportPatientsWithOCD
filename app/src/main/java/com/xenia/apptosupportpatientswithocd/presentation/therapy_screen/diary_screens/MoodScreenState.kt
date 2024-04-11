package com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.diary_screens

import com.xenia.apptosupportpatientswithocd.domain.entity.MoodModel

sealed class MoodScreenState {
    data object Initial: MoodScreenState()
    data object Loading: MoodScreenState()
    data class MoodsMain(
        val moodList: List<MoodModel>?
    ) : MoodScreenState()
}