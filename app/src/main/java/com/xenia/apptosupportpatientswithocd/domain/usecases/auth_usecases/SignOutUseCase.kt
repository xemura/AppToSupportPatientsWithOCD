package com.xenia.apptosupportpatientswithocd.domain.usecases.auth_usecases

import com.xenia.apptosupportpatientswithocd.domain.repository.AuthRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke() {
        return repository.signOut()
    }
}