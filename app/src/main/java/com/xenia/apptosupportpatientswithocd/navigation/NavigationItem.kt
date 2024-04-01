package com.xenia.apptosupportpatientswithocd.navigation

import android.net.Uri
import androidx.compose.ui.graphics.Color
import com.google.gson.Gson
import com.xenia.apptosupportpatientswithocd.R

enum class Screen {
    MAIN,

    MODULE,
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
        Screen.MODULE.name
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

    data object ListModules : NavigationItem(
        "Модули",
        R.drawable.modules,
        Screen.MODULES_LIST.name
    )

    data object ModuleContent : NavigationItem(
        title = "Список статей",
        route =  "${Screen.MODULE_CONTENT.name}/{content_list}"
    ) {
        fun getRouteWithArgs(contentList: List<com.xenia.apptosupportpatientswithocd.presentation.modules_screen.model.ModuleContent>) : String {
            val contentJson = Gson().toJson(contentList)
            return "${Screen.MODULE_CONTENT.name}/${contentJson.encode()}"
        }
    }

    data object ContentText : NavigationItem(
        title = "Содержимое контента",
        route =  "${Screen.CONTENT_TEXT.name}/{content_text}"
    ) {
        fun getRouteWithArgs(text: String) : String {
            return "${Screen.CONTENT_TEXT.name}/${text}"
        }
    }
}

fun String.encode() : String {
    return Uri.encode(this)
}