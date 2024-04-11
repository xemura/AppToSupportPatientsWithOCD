package com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.diary_screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.apptosupportpatientswithocd.domain.usecases.mood_usecases.DeleteMoodUseCase
import com.xenia.apptosupportpatientswithocd.domain.usecases.mood_usecases.GetMoodByIDUseCase
import com.xenia.apptosupportpatientswithocd.domain.usecases.mood_usecases.GetMoodsUseCase
import com.xenia.apptosupportpatientswithocd.domain.usecases.mood_usecases.SaveNewMoodUseCase
import com.xenia.apptosupportpatientswithocd.domain.usecases.mood_usecases.UpdateMoodByIDUseCase
import com.xenia.apptosupportpatientswithocd.presentation.profile_screen.ProfileScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoodViewModel @Inject constructor(
    private val getMoodsUseCase: GetMoodsUseCase,
    private val saveNewMoodUseCase: SaveNewMoodUseCase,
    private val updateMoodByIDUseCase: UpdateMoodByIDUseCase,
    private val getMoodByIDUseCase: GetMoodByIDUseCase,
    private val deleteMoodUseCase: DeleteMoodUseCase
) : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private val moodsFlow = getMoodsUseCase()

    val screenState = moodsFlow
        .map { MoodScreenState.MoodsMain(moodList = it) as MoodScreenState }
        .onStart {
            emit(MoodScreenState.Loading)
        }.stateIn(
            scope = coroutineScope,
            started = SharingStarted.Lazily,
            initialValue = ProfileScreenState.Initial
        )

    fun saveMood(assessment: Int, note: String) {
        viewModelScope.launch {
            saveNewMoodUseCase(assessment, note)
        }
    }

    fun deleteMood(id: String) {
        viewModelScope.launch {
            deleteMoodUseCase(id)
        }
    }
}