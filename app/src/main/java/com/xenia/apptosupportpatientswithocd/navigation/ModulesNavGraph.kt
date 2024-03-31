package com.xenia.apptosupportpatientswithocd.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.modulesScreenNavGraph(
    modulesScreenContent: @Composable () -> Unit,
    moduleContentScreenContent: @Composable () -> Unit,
    contentTextScreenContent: @Composable () -> Unit,
) {
    navigation(
        startDestination = NavigationItem.ModulesList.route,
        route = NavigationItem.Modules.route
    ) {
        composable(NavigationItem.ModulesList.route) {
            modulesScreenContent()
        }
        composable(NavigationItem.ModuleContent.route) {
            moduleContentScreenContent()
        }
        composable(NavigationItem.ContentText.route) {
            contentTextScreenContent()
        }
    }
}