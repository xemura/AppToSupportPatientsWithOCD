package com.xenia.apptosupportpatientswithocd.di

import android.content.Context
import com.xenia.apptosupportpatientswithocd.data.repository.MainRepositoryImpl
import com.xenia.apptosupportpatientswithocd.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepository(impl: MainRepositoryImpl): MainRepository
}