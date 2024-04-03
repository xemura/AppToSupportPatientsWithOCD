package com.xenia.apptosupportpatientswithocd.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.diaryNavGraph(
    dairyMainScreenContent: @Composable () -> Unit,
    addMoodScreenContent: @Composable () -> Unit,
) {
    navigation(
        startDestination = NavigationItem.DiaryMain.route,
        route = NavigationItem.Diary.route
    ) {
        composable(NavigationItem.DiaryMain.route) {
            dairyMainScreenContent()
        }

        composable(NavigationItem.AddMood.route) {
            addMoodScreenContent()
        }
    }
}