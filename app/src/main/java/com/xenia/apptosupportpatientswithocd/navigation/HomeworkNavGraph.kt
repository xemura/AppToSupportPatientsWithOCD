package com.xenia.apptosupportpatientswithocd.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xenia.apptosupportpatientswithocd.domain.entity.HomeworkModel
import com.xenia.apptosupportpatientswithocd.domain.entity.MoodModel
import java.lang.reflect.Type

fun NavGraphBuilder.homeworkNavGraph(
    mainHomeworkScreenContent: @Composable () -> Unit,
    addHomeworkScreenContent:  @Composable () -> Unit,
    editHomeworkScreenContent: @Composable (HomeworkModel) -> Unit,
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

        composable(
            route = NavigationItem.EditHomework.route,
            arguments = listOf(
                navArgument("obj_homework") {
                    type = NavType.StringType
                },
            )
        ) {
            val homeworkJson = it.arguments?.getString("obj_homework") ?: ""
            val objectHomework: Type = object : TypeToken<HomeworkModel?>(){}.type
            val homework: HomeworkModel = Gson().fromJson(homeworkJson, objectHomework)

            editHomeworkScreenContent(homework)
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