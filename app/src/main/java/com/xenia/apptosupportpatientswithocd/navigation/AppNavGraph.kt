package com.xenia.apptosupportpatientswithocd.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xenia.apptosupportpatientswithocd.presentation.modules_screen.model.ModuleContent

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    mainScreenContent: @Composable () -> Unit,
    modulesScreenContent: @Composable () -> Unit,
    moduleContentScreenContent: @Composable (List<ModuleContent>) -> Unit,
    contentTextScreenContent: @Composable (String) -> Unit,
    profileScreenContent: @Composable () -> Unit,
    scriptsScreenContent: @Composable () -> Unit,
    therapyScreenContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationItem.Main.route
    ) {

        modulesScreenNavGraph(
            modulesScreenContent,
            moduleContentScreenContent,
            contentTextScreenContent
        )

        composable(NavigationItem.Main.route) {
            mainScreenContent()
        }

        composable(NavigationItem.Profile.route) {
            profileScreenContent()
        }

        composable(NavigationItem.Scripts.route) {
            scriptsScreenContent()
        }

        composable(NavigationItem.Therapy.route) {
            therapyScreenContent()
        }
    }
}