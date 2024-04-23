package com.xenia.apptosupportpatientswithocd.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xenia.apptosupportpatientswithocd.presentation.ViewModelFactory
import com.xenia.apptosupportpatientswithocd.presentation.auth_screen.AuthViewModel
import com.xenia.apptosupportpatientswithocd.presentation.modules_screen.ModulesViewModel
import com.xenia.apptosupportpatientswithocd.presentation.profile_screen.ProfileViewModel
import com.xenia.apptosupportpatientswithocd.presentation.scripts_screen.ScriptViewModel
import com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.diary_screens.MoodViewModel
import com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.homework_screens.HomeworkViewModel
import com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.practice_screens.StatisticHomeworkViewModel
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