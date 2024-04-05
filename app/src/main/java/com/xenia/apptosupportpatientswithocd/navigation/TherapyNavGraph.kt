package com.xenia.apptosupportpatientswithocd.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.diary_screens.Mood

fun NavGraphBuilder.therapyNavGraph(
    therapyScreenContent: @Composable () -> Unit,

    diaryMoodScreenContent: @Composable () -> Unit,
    addMoodScreenContent: @Composable () -> Unit,
    editMoodScreenContent: @Composable (Mood) -> Unit,

    homeworkScreenContent: @Composable () -> Unit,
    addHomeworkScreenContent:  @Composable () -> Unit,
    statisticHomeworkScreenContent: @Composable () -> Unit,

    stateBeforePracticeHomework: @Composable () -> Unit,
    stateAfterPracticeHomework: @Composable () -> Unit,
    practiceContentHomework: @Composable () -> Unit,
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
            addMoodScreenContent,
            editMoodScreenContent
        )

        homeworkNavGraph(
            homeworkScreenContent,
            addHomeworkScreenContent,
            statisticHomeworkScreenContent,

            stateBeforePracticeHomework,
            stateAfterPracticeHomework,
            practiceContentHomework
        )

    }
}