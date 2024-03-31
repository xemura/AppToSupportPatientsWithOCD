package com.xenia.apptosupportpatientswithocd.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xenia.apptosupportpatientswithocd.navigation.AppNavGraph
import com.xenia.apptosupportpatientswithocd.navigation.NavigationItem
import com.xenia.apptosupportpatientswithocd.navigation.rememberNavigationState
import com.xenia.apptosupportpatientswithocd.presentation.auth_screen.AuthViewModel
import com.xenia.apptosupportpatientswithocd.presentation.bottom_navigation.GlassmorphicBottomNavigation
import com.xenia.apptosupportpatientswithocd.presentation.main_screen.MainScreen
import com.xenia.apptosupportpatientswithocd.presentation.modules_screen.ContentTextScreen
import com.xenia.apptosupportpatientswithocd.presentation.modules_screen.ModuleContentScreen
import com.xenia.apptosupportpatientswithocd.presentation.modules_screen.ModulesScreen
import com.xenia.apptosupportpatientswithocd.presentation.profile_screen.ProfileScreen
import com.xenia.apptosupportpatientswithocd.presentation.scripts_screen.ScriptsScreen
import com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.TherapyScreen
import dev.chrisbanes.haze.HazeState

@Composable
fun EnterMainScreen() {
    val navigationState = rememberNavigationState()

    val hazeState = remember { HazeState() }

    val tabs = listOf(
        NavigationItem.Modules,
        NavigationItem.Scripts,
        NavigationItem.Main,
        NavigationItem.Therapy,
        NavigationItem.Profile
    )

    val component = getApplicationComponent()
    val viewModel: AuthViewModel = viewModel(factory = component.getViewModelFactory())

    Scaffold(
        bottomBar = {
            GlassmorphicBottomNavigation(
                navigationState,
                hazeState,
                tabs
            )
        }
    ) { contentPadding ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            mainScreenContent = {
                MainScreen(
                    paddingValues = contentPadding,
                )
            },
            modulesScreenContent = {
                ModulesScreen()
            },
            moduleContentScreenContent = {
                ModuleContentScreen()
            },
            contentTextScreenContent = {
                ContentTextScreen()
            },
            profileScreenContent = {
                ProfileScreen(viewModel)
            },
            scriptsScreenContent = {
                ScriptsScreen()
            },
            therapyScreenContent = {
                TherapyScreen()
            },
        )
    }
}

