package com.xenia.apptosupportpatientswithocd.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Star
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.xenia.apptosupportpatientswithocd.R

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
    val icon: Int = R.drawable.therapy,
    val route: String,
    val color: Color = Color(0xFF00ACFF)
) {
    data object Main : NavigationItem(
        "Главная",
        R.drawable.home,
        Screen.MAIN.name
    )

    // color = Color(0xFF00a6fb)

    data object Modules : NavigationItem(
        "Модули",
        R.drawable.modules,
        Screen.MODULES.name
    )

    data object Profile : NavigationItem(
        "Профиль",
        icon = R.drawable.profile,
        Screen.PROFILE.name
    )

    data object Scripts : NavigationItem(
        "Ритуалы",
        R.drawable.scripts,
        Screen.SCRIPTS.name
    )

    data object Therapy : NavigationItem(
        "Терапия",
        R.drawable.therapy,
        Screen.THERAPY.name
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