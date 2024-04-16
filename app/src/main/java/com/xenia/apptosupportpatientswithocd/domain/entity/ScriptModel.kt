package com.xenia.apptosupportpatientswithocd.domain.entity

data class ScriptModel(
    val id: String,
    val name: String,
    val dropDownBoxEnabled: Boolean,
    val listActions: List<Action>?
)