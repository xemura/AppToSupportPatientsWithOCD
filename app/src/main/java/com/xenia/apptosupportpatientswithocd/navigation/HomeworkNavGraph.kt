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
import com.xenia.apptosupportpatientswithocd.domain.entity.StatisticModel
import java.lang.reflect.Type

fun NavGraphBuilder.homeworkNavGraph(
    mainHomeworkScreenContent: @Composable () -> Unit,
    addHomeworkScreenContent:  @Composable () -> Unit,
    editHomeworkScreenContent: @Composable (HomeworkModel) -> Unit,
    statisticHomeworkScreenContent: @Composable (HomeworkModel) -> Unit,

    stateBeforePracticeHomework: @Composable (HomeworkModel) -> Unit,
    stateAfterPracticeHomework: @Composable (StatisticModel) -> Unit,
    practiceContentHomework: @Composable (HomeworkModel, StatisticModel) -> Unit,
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

        composable(
            route = NavigationItem.StatisticHomework.route,
            arguments = listOf(
                navArgument("homework") {
                    type = NavType.StringType
                },
            )
        ) {
            val homeworkJson = it.arguments?.getString("homework") ?: ""
            val objectHomework: Type = object : TypeToken<HomeworkModel?>(){}.type
            val homework: HomeworkModel = Gson().fromJson(homeworkJson, objectHomework)

            statisticHomeworkScreenContent(homework)
        }

        composable(
            route = NavigationItem.AfterPracticeHomework.route,
            arguments = listOf(
                navArgument("obj_statistic") {
                    type = NavType.StringType
                },
            )
        ) {
            val statisticJson = it.arguments?.getString("obj_statistic") ?: ""
            val objectStatistic: Type = object : TypeToken<StatisticModel?>(){}.type
            val statistic: StatisticModel = Gson().fromJson(statisticJson, objectStatistic)

            stateAfterPracticeHomework(statistic)
        }

        composable(
            route = NavigationItem.PracticeHomework.route,
            arguments = listOf(
                navArgument("obj_homework") {
                    type = NavType.StringType
                },
                navArgument("obj_statistic") {
                    type = NavType.StringType
                },
            )
        ) {
            val statisticJson = it.arguments?.getString("obj_statistic") ?: ""
            val objectStatistic: Type = object : TypeToken<StatisticModel?>(){}.type
            val statistic: StatisticModel = Gson().fromJson(statisticJson, objectStatistic)

            val homeworkJson = it.arguments?.getString("obj_homework") ?: ""
            val objectHomework: Type = object : TypeToken<HomeworkModel?>(){}.type
            val homework: HomeworkModel = Gson().fromJson(homeworkJson, objectHomework)

            practiceContentHomework(homework, statistic)
        }

        composable(
            route = NavigationItem.BeforePracticeHomework.route,
            arguments = listOf(
                navArgument("obj_homework") {
                    type = NavType.StringType
                },
            )
        ) {
            val homeworkJson = it.arguments?.getString("obj_homework") ?: ""
            val objectHomework: Type = object : TypeToken<HomeworkModel?>(){}.type
            val homework: HomeworkModel = Gson().fromJson(homeworkJson, objectHomework)

            stateBeforePracticeHomework(homework)
        }
    }
}