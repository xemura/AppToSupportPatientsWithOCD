package com.xenia.apptosupportpatientswithocd.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xenia.apptosupportpatientswithocd.presentation.modules_screen.model.ModuleContent
import java.lang.reflect.Type


fun NavGraphBuilder.modulesScreenNavGraph(
    modulesScreenContent: @Composable () -> Unit,
    moduleContentScreenContent: @Composable (List<ModuleContent>) -> Unit,
    contentTextScreenContent: @Composable (String) -> Unit,
) {
    navigation(
        startDestination = NavigationItem.ListModules.route,
        route = NavigationItem.Modules.route
    ) {
        composable(NavigationItem.ListModules.route) {
            modulesScreenContent()
        }

        composable(
            route = NavigationItem.ModuleContent.route,
            arguments = listOf(
                navArgument("content_list") {
                    type = NavType.StringType
                },
            )
        ) {
            val contentJson = it.arguments?.getString("content_list") ?: ""
            val listOfObjects: Type = object : TypeToken<ArrayList<ModuleContent?>?>() {}.type
            val contentModule: List<ModuleContent> = Gson().fromJson(contentJson, listOfObjects)

            moduleContentScreenContent(contentModule)
        }

        composable(
            route = NavigationItem.ContentText.route,
            arguments = listOf(
                navArgument("content_text") {
                    type = NavType.StringType
                },
            )
        ) {
            val text = it.arguments?.getString("content_text") ?: ""

            contentTextScreenContent(text)
        }
    }
}