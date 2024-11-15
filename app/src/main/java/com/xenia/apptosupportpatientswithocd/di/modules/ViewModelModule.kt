package com.xenia.apptosupportpatientswithocd.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xenia.apptosupportpatientswithocd.di.ViewModelKey
import com.xenia.apptosupportpatientswithocd.presentation.viewmodels.ViewModelFactory
import com.xenia.apptosupportpatientswithocd.presentation.viewmodels.AuthViewModel
import com.xenia.apptosupportpatientswithocd.presentation.viewmodels.ModulesViewModel
import com.xenia.apptosupportpatientswithocd.presentation.viewmodels.ProfileViewModel
import com.xenia.apptosupportpatientswithocd.presentation.viewmodels.ScriptViewModel
import com.xenia.apptosupportpatientswithocd.presentation.viewmodels.MoodViewModel
import com.xenia.apptosupportpatientswithocd.presentation.viewmodels.HomeworkViewModel
import com.xenia.apptosupportpatientswithocd.presentation.viewmodels.StatisticHomeworkViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    @Binds
    fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ModulesViewModel::class)
    @Binds
    fun bindModulesViewModel(viewModel: ModulesViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    @Binds
    fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @IntoMap
    @ViewModelKey(MoodViewModel::class)
    @Binds
    fun bindMoodsViewModel(viewModel: MoodViewModel): ViewModel

    @IntoMap
    @ViewModelKey(HomeworkViewModel::class)
    @Binds
    fun bindHomeworksViewModel(viewModel: HomeworkViewModel): ViewModel

    @IntoMap
    @ViewModelKey(StatisticHomeworkViewModel::class)
    @Binds
    fun bindStatisticViewModel(viewModel: StatisticHomeworkViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ScriptViewModel::class)
    @Binds
    fun bindScriptViewModel(viewModel: ScriptViewModel): ViewModel
}