package com.xenia.apptosupportpatientswithocd.presentation.profile_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.apptosupportpatientswithocd.domain.usecases.profile_usecases.GetUserInfoUseCase
import com.xenia.apptosupportpatientswithocd.domain.usecases.profile_usecases.UpdateUserInfoUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val updateUserInfoUseCase: UpdateUserInfoUseCase
) : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private val userFlow = getUserInfoUseCase()

    val screenState = userFlow
        .filter { it.name.isNotEmpty() }
        .map { ProfileScreenState.Profile(userInfo = it) as ProfileScreenState }
        .onStart {
            Log.d("TAG", "onStart")
            emit(ProfileScreenState.Loading)
        }.stateIn(
            scope = coroutineScope,
            started = SharingStarted.Lazily,
            initialValue = ProfileScreenState.Initial
        )

    fun saveChanges(name: String, notificationEnable: Boolean, notificationTime: String) {
        viewModelScope.launch {
            updateUserInfoUseCase.updateUserInfo(name, notificationEnable, notificationTime)
        }
    }
}