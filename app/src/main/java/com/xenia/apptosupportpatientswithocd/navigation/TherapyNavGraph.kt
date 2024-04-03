package com.xenia.apptosupportpatientswithocd.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.therapyNavGraph(
    therapyScreenContent: @Composable () -> Unit,
    diaryMoodScreenContent: @Composable () -> Unit,
    addMoodScreenContent: @Composable () -> Unit,
    homeworkScreenContent: @Composable () -> Unit,
) {
    navigation(
        startDestination = NavigationItem.TherapyContent.route,
        route = NavigationItem.Therapy.route
    ) {
        composable(NavigationItem.TherapyContent.route) {
            therapyScreenContent()
        }

        diaryNavGraph(
            diaryMoodScreenContent,
            addMoodScreenContent
        )

        homeworkNavGraph(
            homeworkScreenContent
        )

    }
}