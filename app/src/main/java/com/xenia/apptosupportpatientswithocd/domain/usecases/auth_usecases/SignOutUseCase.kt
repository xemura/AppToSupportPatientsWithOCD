package com.xenia.apptosupportpatientswithocd.domain.usecases.auth_usecases

import com.google.firebase.auth.AuthResult
import com.xenia.apptosupportpatientswithocd.domain.repository.AuthRepository
import com.xenia.apptosupportpatientswithocd.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke() {
        return repository.signOut()
    }
}