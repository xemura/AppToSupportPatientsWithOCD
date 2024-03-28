package com.xenia.apptosupportpatientswithocd.di

import androidx.lifecycle.ViewModel
import com.xenia.apptosupportpatientswithocd.presentation.main_screen.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}