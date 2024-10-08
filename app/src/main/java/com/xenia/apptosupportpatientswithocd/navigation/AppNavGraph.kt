package com.xenia.apptosupportpatientswithocd.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xenia.apptosupportpatientswithocd.domain.entity.HomeworkModel
import com.xenia.apptosupportpatientswithocd.domain.entity.ModuleContentModel
import com.xenia.apptosupportpatientswithocd.domain.entity.MoodModel
import com.xenia.apptosupportpatientswithocd.domain.entity.StatisticModel

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    mainScreenContent: @Composable () -> Unit,

    modulesScreenContent: @Composable () -> Unit,
    moduleContentScreenContent: @Composable (List<ModuleContentModel>) -> Unit,
    contentTextScreenContent: @Composable (String) -> Unit,

    profileScreenContent: @Composable () -> Unit,

    scriptsContentScreen: @Composable () -> Unit,
    addScriptScreen: @Composable () -> Unit,

    therapyScreenContent: @Composable () -> Unit,

    diaryMoodScreenContent: @Composable () -> Unit,
    addMoodScreenContent: @Composable () -> Unit,
    editMoodScreenContent: @Composable (MoodModel) -> Unit,
    allMoodsScreenContent: @Composable () -> Unit,

    homeworkScreenContent: @Composable () -> Unit,
    addHomeworkScreenContent: @Composable () -> Unit,
    editHomeworkScreenContent: @Composable (HomeworkModel) -> Unit,
    statisticHomeworkScreenContent: @Composable (HomeworkModel) -> Unit,

    stateBeforePracticeHomework: @Composable (HomeworkModel) -> Unit,
    stateAfterPracticeHomework: @Composable (StatisticModel) -> Unit,
    practiceContentHomework: @Composable (HomeworkModel, StatisticModel) -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationItem.Main.route
    ) {

        modulesScreenNavGraph(
            modulesScreenContent,
            moduleContentScreenContent,
            contentTextScreenContent
        )

        scriptsNavGraph(
            scriptsContentScreen,
            addScriptScreen
        )

        therapyNavGraph(
            therapyScreenContent,

            diaryMoodScreenContent,
            addMoodScreenContent,
            editMoodScreenContent,
            allMoodsScreenContent,

            homeworkScreenContent,
            addHomeworkScreenContent,
            editHomeworkScreenContent,
            statisticHomeworkScreenContent,

            stateBeforePracticeHomework,
            stateAfterPracticeHomework,
            practiceContentHomework,
        )

        composable(NavigationItem.Main.route) {
            mainScreenContent()
        }

        composable(NavigationItem.Profile.route) {
            profileScreenContent()
        }
    }
}