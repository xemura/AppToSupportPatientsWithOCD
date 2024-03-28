package com.xenia.apptosupportpatientswithocd.data.repository

import com.xenia.apptosupportpatientswithocd.domain.entity.AuthState
import com.xenia.apptosupportpatientswithocd.domain.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(): MainRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private val checkAuthStateEvents = MutableSharedFlow<Unit>(replay = 1)
    private val authStateFlow = flow {
        checkAuthStateEvents.emit(Unit)
        checkAuthStateEvents.collect {
//            val currentToken = token
//            val loggedIn = currentToken != null && currentToken.isValid
//            val authState = if (loggedIn) AuthState.Authorized else AuthState.NotAuthorized

            val authState = AuthState.Authorized

            emit(authState)
        }
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = AuthState.Initial
    )

    override fun getAuthStateFlow(): StateFlow<AuthState> = authStateFlow
    override suspend fun checkAuthState() {
        checkAuthStateEvents.emit(Unit)
    }
}