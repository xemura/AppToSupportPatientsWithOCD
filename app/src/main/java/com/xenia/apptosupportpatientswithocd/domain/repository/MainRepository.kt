package com.xenia.apptosupportpatientswithocd.domain.repository

import com.xenia.apptosupportpatientswithocd.domain.entity.AuthState
import kotlinx.coroutines.flow.StateFlow

interface MainRepository {
    fun getAuthStateFlow(): StateFlow<AuthState>
    suspend fun checkAuthState()
}