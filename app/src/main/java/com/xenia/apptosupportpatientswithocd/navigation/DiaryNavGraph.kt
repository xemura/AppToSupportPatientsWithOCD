package com.xenia.apptosupportpatientswithocd.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.diary_screens.Mood
import java.lang.reflect.Type

fun NavGraphBuilder.diaryNavGraph(
    dairyMainScreenContent: @Composable () -> Unit,
    addMoodScreenContent: @Composable () -> Unit,
    editMoodScreenContent: @Composable (Mood) -> Unit,
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

        composable(
            route = NavigationItem.EditMood.route,
            arguments = listOf(
                navArgument("obj_mood") {
                    type = NavType.StringType
                },
            )
        ) {
            val moodJson = it.arguments?.getString("obj_mood") ?: ""
            val objectMood: Type = object : TypeToken<Mood?>(){}.type
            val mood: Mood = Gson().fromJson(moodJson, objectMood)

            editMoodScreenContent(mood)
        }
    }
}