package com.xenia.apptosupportpatientswithocd.presentation.states

data class SignInState (
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)