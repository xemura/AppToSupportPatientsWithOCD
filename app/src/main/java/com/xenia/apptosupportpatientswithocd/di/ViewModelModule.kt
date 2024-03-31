package com.xenia.apptosupportpatientswithocd.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xenia.apptosupportpatientswithocd.presentation.ViewModelFactory
import com.xenia.apptosupportpatientswithocd.presentation.auth_screen.AuthViewModel
import com.xenia.apptosupportpatientswithocd.presentation.main_screen.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    @Binds
    fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel
}