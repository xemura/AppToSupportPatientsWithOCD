package com.xenia.apptosupportpatientswithocd.di

import com.xenia.apptosupportpatientswithocd.domain.repository.AuthRepository
import com.xenia.apptosupportpatientswithocd.data.repository.AuthRepositoryImpl
import com.xenia.apptosupportpatientswithocd.data.repository.MainRepositoryImpl
import com.xenia.apptosupportpatientswithocd.domain.repository.MainRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindMainRepository(impl: MainRepositoryImpl): MainRepository

    @ApplicationScope
    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}