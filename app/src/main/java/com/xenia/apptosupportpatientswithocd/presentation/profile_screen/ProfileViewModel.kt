package com.xenia.apptosupportpatientswithocd.presentation.profile_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.xenia.apptosupportpatientswithocd.domain.usecases.profile_usecases.GetUserInfoUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase
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


//    val screenState = userFlow
//        .filter { it.name.isNotEmpty() }
//        .map { ProfileScreenState.Profile(userInfo = it) as ProfileScreenState }
//        .onStart {
//            Log.d("TAG", "onStart")
//            emit(ProfileScreenState.Loading)
//        }
}