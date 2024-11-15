package com.xenia.apptosupportpatientswithocd.presentation.states

import com.xenia.apptosupportpatientswithocd.domain.entity.MoodModel

sealed class MoodScreenState {
    data object Initial: MoodScreenState()
    data object Loading: MoodScreenState()
    data class MoodsMain(val moodList: List<MoodModel>?) : MoodScreenState()
}