package com.xenia.apptosupportpatientswithocd.domain.entity

data class ModuleModel(
    val id: String,
    val name: String,
    val image: Int,
    val contentList: List<ModuleContentModel>
)