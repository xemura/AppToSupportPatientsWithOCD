package com.xenia.apptosupportpatientswithocd.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
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
    PROFILE,

    SCRIPTS,
    THERAPY,
}


sealed class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
    val color: Color
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
}