package com.xenia.apptosupportpatientswithocd.domain.usecases

import com.xenia.apptosupportpatientswithocd.domain.repository.AuthRepository
import com.xenia.apptosupportpatientswithocd.domain.entity.AuthState
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetAuthStateFlowUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(): StateFlow<AuthState> {
        return repository.getAuthStateFlow()
    }
}