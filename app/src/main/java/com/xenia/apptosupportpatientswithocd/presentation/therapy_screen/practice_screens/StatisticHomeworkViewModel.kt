package com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.practice_screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.apptosupportpatientswithocd.domain.usecases.homework_usecases.SetStatisticHomeworkByIDUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class StatisticHomeworkViewModel @Inject constructor(
    private val setStatisticHomeworkByIDUseCase: SetStatisticHomeworkByIDUseCase
) : ViewModel() {

    fun setStatisticHomeworkByID(statisticModel: StatisticModel) {
        viewModelScope.launch {
            setStatisticHomeworkByIDUseCase(statisticModel)
        }
    }
}