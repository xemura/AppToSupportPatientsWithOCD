package com.xenia.apptosupportpatientswithocd.navigation

import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
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
    val iconSelected: ImageVector,
    val iconUnselected: ImageVector,
    val route: String
) {
    data object Main : NavigationItem(
        "Главная",
        Icons.Filled.Home,
        Icons.Outlined.Home,
        Screen.MAIN.name
    )

    data object Modules : NavigationItem(
        "Модули",
        Icons.Filled.Star,
        Icons.Outlined.Star,
        Screen.MODULES.name
    )

    data object Profile : NavigationItem(
        "Профиль",
        Icons.Filled.Person,
        Icons.Outlined.Person,
        Screen.PROFILE.name
    )

    data object Scripts : NavigationItem(
        "Ритуалы",
        Icons.Filled.DateRange,
        Icons.Outlined.DateRange,
        Screen.SCRIPTS.name
    )

    data object Therapy : NavigationItem(
        "Терапия",
        Icons.Filled.Favorite,
        Icons.Outlined.Favorite,
        Screen.THERAPY.name
    )
}