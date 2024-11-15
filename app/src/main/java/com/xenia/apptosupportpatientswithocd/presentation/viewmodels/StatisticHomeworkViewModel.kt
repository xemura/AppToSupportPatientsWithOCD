package com.xenia.apptosupportpatientswithocd.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.apptosupportpatientswithocd.domain.entity.StatisticModel
import com.xenia.apptosupportpatientswithocd.domain.usecases.homework_usecases.GetStatisticHomeworkByIDUseCase
import com.xenia.apptosupportpatientswithocd.domain.usecases.homework_usecases.SetStatisticHomeworkByIDUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class StatisticHomeworkViewModel @Inject constructor(
    private val setStatisticHomeworkByIDUseCase: SetStatisticHomeworkByIDUseCase,
    private val getStatisticHomeworkByIDUseCase: GetStatisticHomeworkByIDUseCase
) : ViewModel() {

    private var _statistics = MutableStateFlow<List<StatisticModel>?>(emptyList())
    val statistics: StateFlow<List<StatisticModel>?> = _statistics

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    fun setStatisticHomeworkByID(statisticModel: StatisticModel) {
        viewModelScope.launch {
            setStatisticHomeworkByIDUseCase(statisticModel)
        }
    }

    fun getStatisticByID(id: String) {
        coroutineScope.launch {
            getStatisticHomeworkByIDUseCase(id).collect {
                _statistics.value = it
            }
            _statistics.value
        }
    }
}