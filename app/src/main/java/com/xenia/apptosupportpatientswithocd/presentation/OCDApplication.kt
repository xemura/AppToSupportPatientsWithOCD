package com.xenia.apptosupportpatientswithocd.presentation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.xenia.apptosupportpatientswithocd.di.ApplicationComponent
import com.xenia.apptosupportpatientswithocd.di.DaggerApplicationComponent

class OCDApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}

@Composable
fun getApplicationComponent(): ApplicationComponent {
    return (LocalContext.current.applicationContext as OCDApplication).component
}