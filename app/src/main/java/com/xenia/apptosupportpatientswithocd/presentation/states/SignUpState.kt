package com.xenia.apptosupportpatientswithocd.presentation.states

data class SignUpState (
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)