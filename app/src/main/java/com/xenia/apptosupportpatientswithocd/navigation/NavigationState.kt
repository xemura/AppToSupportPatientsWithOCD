package com.xenia.apptosupportpatientswithocd.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.xenia.apptosupportpatientswithocd.domain.entity.HomeworkModel
import com.xenia.apptosupportpatientswithocd.domain.entity.ModuleContentModel
import com.xenia.apptosupportpatientswithocd.domain.entity.MoodModel

class NavigationState(
    val navHostController: NavHostController
) {
    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToContentModule(contentList: List<ModuleContentModel>) {
        navHostController.navigate(NavigationItem.ModuleContent.getRouteWithArgs(contentList))
    }

    fun navigateToContentText(text: String) {
        navHostController.navigate(NavigationItem.ContentText.getRouteWithArgs(text))
    }

    fun navigateToEditMood(mood: MoodModel) {
        navHostController.navigate(NavigationItem.EditMood.getRouteWithArgs(mood))
    }

    fun navigateToEditHomework(homework: HomeworkModel) {
        navHostController.navigate(NavigationItem.EditHomework.getRouteWithArgs(homework))
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
) : NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}