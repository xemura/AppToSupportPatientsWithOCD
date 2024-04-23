package com.xenia.apptosupportpatientswithocd.domain.usecases.auth_usecases

import com.xenia.apptosupportpatientswithocd.domain.entity.AuthState
import com.xenia.apptosupportpatientswithocd.domain.repository.AuthRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetAuthStateFlowUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(): StateFlow<AuthState> {
        return repository.getAuthStateFlow()
    }
}