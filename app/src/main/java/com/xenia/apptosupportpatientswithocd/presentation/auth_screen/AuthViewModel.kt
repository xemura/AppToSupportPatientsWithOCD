package com.xenia.apptosupportpatientswithocd.presentation.auth_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.apptosupportpatientswithocd.domain.repository.AuthRepository
import com.xenia.apptosupportpatientswithocd.domain.usecases.GetAuthStateFlowUseCase
import com.xenia.apptosupportpatientswithocd.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    getAuthStateFlowUseCase: GetAuthStateFlowUseCase,
): ViewModel() {

    val authState = getAuthStateFlowUseCase()

    private val _signInState = Channel<SignInState>()
    val signInState = _signInState.receiveAsFlow()

    private val _signUpState = Channel<SignUpState>()
    val signUpState = _signUpState.receiveAsFlow()

    fun loginUser(
        email: String, password: String
    ) = viewModelScope.launch {
        repository.loginUser(email, password).collect { result ->
            when(result) {
                is Resource.Success -> {
                    _signInState.send(SignInState(isSuccess = "Sign In Success"))
                }
                is Resource.Error -> {
                    _signInState.send(SignInState(isError = result.message))
                }
                is Resource.Loading -> {
                    _signInState.send(SignInState(isLoading = true))
                }
            }
        }
    }

    fun registerUser(
        email: String, password: String
    ) = viewModelScope.launch {
        repository.registerUser(email, password).collect { result ->
            when(result) {
                is Resource.Success -> {
                    _signUpState.send(SignUpState(isSuccess = "Sign Up Success"))
                }
                is Resource.Error -> {
                    _signUpState.send(SignUpState(isError = result.message))
                }
                is Resource.Loading -> {
                    _signUpState.send(SignUpState(isLoading = true))
                }
            }
        }
    }

    fun signOut() {
        repository.signOut()
    }
}