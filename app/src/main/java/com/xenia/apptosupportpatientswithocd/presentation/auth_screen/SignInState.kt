package com.xenia.apptosupportpatientswithocd.presentation.auth_screen

data class SignInState (
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)