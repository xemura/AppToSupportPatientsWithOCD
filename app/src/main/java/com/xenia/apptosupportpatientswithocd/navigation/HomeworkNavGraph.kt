package com.xenia.apptosupportpatientswithocd.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.homeworkNavGraph(
    mainHomeworkScreenContent: @Composable () -> Unit,
    addHomeworkScreenContent:  @Composable () -> Unit,
    statisticHomeworkScreenContent: @Composable () -> Unit,

    stateBeforePracticeHomework: @Composable () -> Unit,
    stateAfterPracticeHomework: @Composable () -> Unit,
    practiceContentHomework: @Composable () -> Unit,
) {
    navigation(
        startDestination = NavigationItem.MainHomework.route,
        route = NavigationItem.Homework.route
    ) {
        composable(NavigationItem.MainHomework.route) {
            mainHomeworkScreenContent()
        }

        composable(NavigationItem.AddHomework.route) {
            addHomeworkScreenContent()
        }

        composable(NavigationItem.StatisticHomework.route) {
            statisticHomeworkScreenContent()
        }

        composable(NavigationItem.BeforePracticeHomework.route) {
            stateBeforePracticeHomework()
        }

        composable(NavigationItem.PracticeHomework.route) {
            practiceContentHomework()
        }

        composable(NavigationItem.AfterPracticeHomework.route) {
            stateAfterPracticeHomework()
        }
    }
}