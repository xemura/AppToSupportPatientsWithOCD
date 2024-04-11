package com.xenia.apptosupportpatientswithocd.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xenia.apptosupportpatientswithocd.domain.entity.MoodModel
import java.lang.reflect.Type

fun NavGraphBuilder.diaryNavGraph(
    dairyMainScreenContent: @Composable () -> Unit,
    addMoodScreenContent: @Composable () -> Unit,
    editMoodScreenContent: @Composable (MoodModel) -> Unit,
    allMoodsScreenContent: @Composable () -> Unit,
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

        composable(NavigationItem.AllMoods.route) {
            allMoodsScreenContent()
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
            val objectMood: Type = object : TypeToken<MoodModel?>(){}.type
            val mood: MoodModel = Gson().fromJson(moodJson, objectMood)

            editMoodScreenContent(mood)
        }
    }
}