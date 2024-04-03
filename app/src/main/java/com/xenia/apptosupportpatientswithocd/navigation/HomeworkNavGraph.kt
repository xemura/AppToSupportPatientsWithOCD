package com.xenia.apptosupportpatientswithocd.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.homeworkNavGraph(
    mainHomeworkScreenContent: @Composable () -> Unit,
) {
    navigation(
        startDestination = NavigationItem.MainHomework.route,
        route = NavigationItem.Homework.route
    ) {
        composable(NavigationItem.MainHomework.route) {
            mainHomeworkScreenContent()
        }
    }
}