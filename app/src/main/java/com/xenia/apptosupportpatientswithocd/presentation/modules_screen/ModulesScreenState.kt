package com.xenia.apptosupportpatientswithocd.presentation.modules_screen

import com.xenia.apptosupportpatientswithocd.domain.entity.ModuleModel

sealed class ModulesScreenState {
    data object Initial: ModulesScreenState()
    data object Loading: ModulesScreenState()
    data class Modules(
        val modulesList: List<ModuleModel>
    ) : ModulesScreenState()
}