package com.xenia.apptosupportpatientswithocd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xenia.apptosupportpatientswithocd.domain.entity.AuthState
import com.xenia.apptosupportpatientswithocd.presentation.EnterMainScreen
import com.xenia.apptosupportpatientswithocd.presentation.getApplicationComponent
import com.xenia.apptosupportpatientswithocd.presentation.main_screen.MainViewModel
import com.xenia.apptosupportpatientswithocd.ui.theme.AppToSupportPatientsWithOCDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val component = getApplicationComponent()
            val viewModel: MainViewModel = viewModel(factory = component.getViewModelFactory())
            val authState = viewModel.authState.collectAsState(AuthState.Initial)

            AppToSupportPatientsWithOCDTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (authState.value) {
                        is AuthState.Authorized -> {
                            EnterMainScreen()
                        }

                        is AuthState.NotAuthorized -> {
                            // окна с авторизацией
                        }

                        else -> {

                        }
                    }
                }
            }
        }
    }
}