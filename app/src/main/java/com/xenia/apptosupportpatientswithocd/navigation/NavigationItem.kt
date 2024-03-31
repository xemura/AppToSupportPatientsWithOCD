package com.xenia.apptosupportpatientswithocd.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Star
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen {
    MAIN,

    MODULES,
    MODULES_LIST,
    MODULE_CONTENT,
    CONTENT_TEXT,

    PROFILE,
    SCRIPTS,

    THERAPY,
}


sealed class NavigationItem(
    val title: String,
    val icon: ImageVector = Icons.Rounded.Star,
    val route: String,
    val color: Color = Color(0xFFF4511E)
) {
    data object Main : NavigationItem(
        "Главная",
        Icons.Rounded.Home,
        Screen.MAIN.name,
        color = Color(0xFFF4511E)
    )

    data object Modules : NavigationItem(
        "Модули",
        Icons.Rounded.Star,
        Screen.MODULES.name,
        color = Color(0xFF8E24AA)
    )

    data object Profile : NavigationItem(
        "Профиль",
        icon = Icons.Rounded.Person,
        Screen.PROFILE.name,
        color = Color(0xFF7CB342)
    )

    data object Scripts : NavigationItem(
        "Ритуалы",
        Icons.Rounded.DateRange,
        Screen.SCRIPTS.name,
        color = Color(0xFF03E5CB)
    )

    data object Therapy : NavigationItem(
        "Терапия",
        Icons.Rounded.Favorite,
        Screen.THERAPY.name,
        color = Color(0xFFFDD835)
    )

    data object ModulesList : NavigationItem(
        title = "Список модулей",
        route =  Screen.MODULES_LIST.name
    )

    data object ModuleContent : NavigationItem(
        title = "Содержимое модуля",
        route =  Screen.MODULE_CONTENT.name
    )

    data object ContentText : NavigationItem(
        title = "Содержимое контента",
        route =  Screen.CONTENT_TEXT.name
    )
}