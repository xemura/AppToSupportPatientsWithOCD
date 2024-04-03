package com.xenia.apptosupportpatientswithocd.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xenia.apptosupportpatientswithocd.presentation.modules_screen.model.ModuleContent

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    mainScreenContent: @Composable () -> Unit,

    modulesScreenContent: @Composable () -> Unit,
    moduleContentScreenContent: @Composable (List<ModuleContent>) -> Unit,
    contentTextScreenContent: @Composable (String) -> Unit,

    profileScreenContent: @Composable () -> Unit,

    scriptsContentScreen: @Composable () -> Unit,
    addScriptScreen: @Composable () -> Unit,

    therapyScreenContent: @Composable () -> Unit,
    diaryMoodScreenContent: @Composable () -> Unit,
    addMoodScreenContent: @Composable () -> Unit,

    homeworkScreenContent: @Composable () -> Unit,
    addHomeworkScreenContent:  @Composable () -> Unit,
    statisticHomeworkScreenContent: @Composable () -> Unit,

    stateBeforePracticeHomework: @Composable () -> Unit,
    stateAfterPracticeHomework: @Composable () -> Unit,
    practiceContentHomework: @Composable () -> Unit,
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

            homeworkScreenContent,
            addHomeworkScreenContent,
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