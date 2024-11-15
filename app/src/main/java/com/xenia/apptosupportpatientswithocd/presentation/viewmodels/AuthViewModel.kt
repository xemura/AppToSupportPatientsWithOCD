package com.xenia.apptosupportpatientswithocd.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.apptosupportpatientswithocd.domain.usecases.auth_usecases.GetAuthStateFlowUseCase
import com.xenia.apptosupportpatientswithocd.domain.usecases.auth_usecases.LoginUserUseCase
import com.xenia.apptosupportpatientswithocd.domain.usecases.auth_usecases.RegisterUserUseCase
import com.xenia.apptosupportpatientswithocd.domain.usecases.auth_usecases.SignOutUseCase
import com.xenia.apptosupportpatientswithocd.presentation.states.SignInState
import com.xenia.apptosupportpatientswithocd.presentation.states.SignUpState
import com.xenia.apptosupportpatientswithocd.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    getAuthStateFlowUseCase: GetAuthStateFlowUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val signOutUseCase: SignOutUseCase
): ViewModel() {

    val authState = getAuthStateFlowUseCase()

    private val _signInState = Channel<SignInState>()
    val signInState = _signInState.receiveAsFlow()

    private val _signUpState = Channel<SignUpState>()
    val signUpState = _signUpState.receiveAsFlow()

    fun loginUser(
        email: String, password: String
    ) = viewModelScope.launch {
        loginUserUseCase.invoke(email, password).collect { result ->
            when(result) {
                is Resource.Success -> {
                    _signInState.send(SignInState(isSuccess = "Вход успешно выполнен"))
                }
                is Resource.Error -> {
                    _signInState.send(SignInState(isError = "Произошла какая-то ошибка"))
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
        registerUserUseCase.invoke(email, password).collect { result ->
            when(result) {
                is Resource.Success -> {
                    _signUpState.send(SignUpState(isSuccess = "Вы успешно зарегистрировались"))
                }
                is Resource.Error -> {
                    _signUpState.send(SignUpState(isError = "Произошла какая-то ошибка"))
                }
                is Resource.Loading -> {
                    _signUpState.send(SignUpState(isLoading = true))
                }
            }
        }
    }

    fun signOut() {
        signOutUseCase.invoke()
    }
}