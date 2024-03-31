package com.xenia.apptosupportpatientswithocd.presentation.auth_screen

data class SignUpState (
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)