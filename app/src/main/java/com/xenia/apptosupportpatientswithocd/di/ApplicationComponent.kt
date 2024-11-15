package com.xenia.apptosupportpatientswithocd.di

import android.content.Context
import com.xenia.apptosupportpatientswithocd.di.modules.DataModule
import com.xenia.apptosupportpatientswithocd.di.modules.FirebaseModule
import com.xenia.apptosupportpatientswithocd.di.modules.ViewModelModule
import com.xenia.apptosupportpatientswithocd.presentation.viewmodels.ViewModelFactory
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        FirebaseModule::class
    ]
)
interface ApplicationComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }

}