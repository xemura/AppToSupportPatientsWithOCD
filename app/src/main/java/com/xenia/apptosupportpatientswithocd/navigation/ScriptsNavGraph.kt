package com.xenia.apptosupportpatientswithocd.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.scriptsNavGraph(
    scriptsScreenContent: @Composable () -> Unit,
    addScriptScreenContent: @Composable () -> Unit,
) {
    navigation(
        startDestination = NavigationItem.ScriptsList.route,
        route = NavigationItem.Scripts.route
    ) {
        composable(NavigationItem.ScriptsList.route) {
            scriptsScreenContent()
        }

        composable(NavigationItem.AddScript.route) {
            addScriptScreenContent()
        }
    }
}