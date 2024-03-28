package com.xenia.apptosupportpatientswithocd.domain.usecases

import com.xenia.apptosupportpatientswithocd.domain.repository.MainRepository
import javax.inject.Inject


class CheckAuthStateUseCase @Inject constructor(
    private val repository: MainRepository
) {

    suspend operator fun invoke() {
        repository.checkAuthState()
    }
}