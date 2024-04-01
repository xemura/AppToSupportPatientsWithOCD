package com.xenia.apptosupportpatientswithocd.presentation.scripts_screen

data class ScriptModel(
    val name: String,
    val dropDownBoxEnabled: Boolean,
    val listActions: List<Action>
)