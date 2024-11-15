package com.xenia.apptosupportpatientswithocd.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.apptosupportpatientswithocd.domain.usecases.profile_usecases.GetUserInfoUseCase
import com.xenia.apptosupportpatientswithocd.domain.usecases.profile_usecases.UpdateUserInfoUseCase
import com.xenia.apptosupportpatientswithocd.presentation.states.ProfileScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
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
        .map { ProfileScreenState.Profile(userInfo = it) as ProfileScreenState }
        .onStart {
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