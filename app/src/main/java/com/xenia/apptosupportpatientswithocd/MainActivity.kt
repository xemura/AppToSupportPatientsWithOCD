package com.xenia.apptosupportpatientswithocd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xenia.apptosupportpatientswithocd.domain.entity.AuthState
import com.xenia.apptosupportpatientswithocd.presentation.screens.EnterMainScreen
import com.xenia.apptosupportpatientswithocd.presentation.viewmodels.ViewModelFactory
import com.xenia.apptosupportpatientswithocd.presentation.viewmodels.AuthViewModel
import com.xenia.apptosupportpatientswithocd.presentation.screens.auth_screen.SignInScreen
import com.xenia.apptosupportpatientswithocd.presentation.getApplicationComponent
import com.xenia.apptosupportpatientswithocd.ui.theme.AppToSupportPatientsWithOCDTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val component = getApplicationComponent()
            val viewModel: AuthViewModel = viewModel(factory = component.getViewModelFactory())
            val authState = viewModel.authState.collectAsState(AuthState.Initial)

            AppToSupportPatientsWithOCDTheme {

                when (authState.value) {
                    is AuthState.Authorized -> {
                        EnterMainScreen()
                    }

                    is AuthState.NotAuthorized -> {
                        SignInScreen(viewModel)
                    }

                    else -> {

                    }
                }
            }
        }
    }
}