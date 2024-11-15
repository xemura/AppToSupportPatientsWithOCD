package com.xenia.apptosupportpatientswithocd.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.apptosupportpatientswithocd.domain.usecases.homework_usecases.AddHomeworkUseCase
import com.xenia.apptosupportpatientswithocd.domain.usecases.homework_usecases.DeleteHomeworkUseCase
import com.xenia.apptosupportpatientswithocd.domain.usecases.homework_usecases.GetHomeworksUseCase
import com.xenia.apptosupportpatientswithocd.domain.usecases.homework_usecases.UpdateHomeworkByIdUseCase
import com.xenia.apptosupportpatientswithocd.presentation.states.HomeworkScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeworkViewModel  @Inject constructor(
    private val addHomeworkUseCase: AddHomeworkUseCase,
    private val deleteHomeworkUseCase: DeleteHomeworkUseCase,
    private val getHomeworksUseCase: GetHomeworksUseCase,
    private val updateHomeworkByIdUseCase: UpdateHomeworkByIdUseCase
): ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private val homeworksFlow = getHomeworksUseCase()

    val screenState = homeworksFlow
        .map { HomeworkScreenState.HomeworkMain(homeworksList = it) as HomeworkScreenState }
        .onStart {
            emit(HomeworkScreenState.Loading)
        }.stateIn(
            scope = coroutineScope,
            started = SharingStarted.Lazily,
            initialValue = HomeworkScreenState.Initial
        )

    fun addHomework(
        obsessionInfo: String,
        triggerInfo: String,
        adviceInfo: String
    ) {
        viewModelScope.launch {
            addHomeworkUseCase(obsessionInfo, triggerInfo, adviceInfo)
        }
    }

    fun deleteHomework(id: String) {
        viewModelScope.launch {
            deleteHomeworkUseCase(id)
        }
    }

    fun updateHomeworkById(
        id: String,
        obsessionInfo: String,
        triggerInfo: String,
        adviceInfo: String
    ) {
        viewModelScope.launch {
            updateHomeworkByIdUseCase(id, obsessionInfo, triggerInfo, adviceInfo)
        }
    }

}