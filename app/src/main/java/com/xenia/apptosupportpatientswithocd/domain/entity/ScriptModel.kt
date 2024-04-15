package com.xenia.apptosupportpatientswithocd.domain.entity

data class ScriptModel(
    val name: String,
    val dropDownBoxEnabled: Boolean,
    val listActions: List<Action>?
)