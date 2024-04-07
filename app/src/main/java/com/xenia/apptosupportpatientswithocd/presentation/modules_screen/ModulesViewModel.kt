package com.xenia.apptosupportpatientswithocd.presentation.modules_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.apptosupportpatientswithocd.domain.entity.ModuleModel
import com.xenia.apptosupportpatientswithocd.domain.usecases.modules_usecases.GetModulesListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ModulesViewModel @Inject constructor(
    getModulesListUseCase: GetModulesListUseCase,
) : ViewModel() {

    private val modulesList = getModulesListUseCase()

    fun getModulesList(): List<ModuleModel> {
        return modulesList
    }
}