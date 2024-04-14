package com.xenia.apptosupportpatientswithocd.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.xenia.apptosupportpatientswithocd.domain.entity.HomeworkModel
import com.xenia.apptosupportpatientswithocd.domain.entity.MoodModel
import com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.practice_screens.StatisticModel

fun NavGraphBuilder.therapyNavGraph(
    therapyScreenContent: @Composable () -> Unit,

    diaryMoodScreenContent: @Composable () -> Unit,
    addMoodScreenContent: @Composable () -> Unit,
    editMoodScreenContent: @Composable (MoodModel) -> Unit,
    allMoodsScreenContent: @Composable () -> Unit,

    homeworkScreenContent: @Composable () -> Unit,
    addHomeworkScreenContent:  @Composable () -> Unit,
    editHomeworkScreenContent: @Composable (HomeworkModel) -> Unit,
    statisticHomeworkScreenContent: @Composable () -> Unit,

    stateBeforePracticeHomework: @Composable (HomeworkModel) -> Unit,
    stateAfterPracticeHomework: @Composable (StatisticModel) -> Unit,
    practiceContentHomework: @Composable (HomeworkModel, StatisticModel) -> Unit,
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
            editMoodScreenContent,
            allMoodsScreenContent
        )

        homeworkNavGraph(
            homeworkScreenContent,
            addHomeworkScreenContent,
            editHomeworkScreenContent,
            statisticHomeworkScreenContent,

            stateBeforePracticeHomework,
            stateAfterPracticeHomework,
            practiceContentHomework
        )

    }
}