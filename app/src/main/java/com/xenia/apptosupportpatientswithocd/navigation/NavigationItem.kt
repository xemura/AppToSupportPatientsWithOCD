package com.xenia.apptosupportpatientswithocd.navigation

import android.net.Uri
import androidx.compose.ui.graphics.Color
import com.google.gson.Gson
import com.xenia.apptosupportpatientswithocd.R
import com.xenia.apptosupportpatientswithocd.domain.entity.ModuleContentModel
import com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.diary_screens.Mood

enum class Screen {
    MAIN,

    MODULE,
    MODULES_LIST,
    MODULE_CONTENT,
    CONTENT_TEXT,

    PROFILE,

    SCRIPTS,
    SCRIPTS_LIST,
    ADD_SCRIPT,

    THERAPY,
    THERAPY_CONTENT,

    DIARY,
    DIARY_MOOD,
    ADD_MOOD,
    EDIT_MOOD,

    HOMEWORK,
    MAIN_HOMEWORK,
    ADD_HOMEWORK,
    STATISTIC_HOMEWORK,

    BEFORE_PRACTICE_HOMEWORK,
    PRACTICE_CONTENT,
    AFTER_PRACTICE_HOMEWORK,
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

    data object ScriptsList : NavigationItem(
        title= "Ритуалы",
        route = Screen.SCRIPTS_LIST.name
    )

    data object AddScript : NavigationItem(
        title = "Создать сценарий",
        route = Screen.ADD_SCRIPT.name
    )

    data object Therapy : NavigationItem(
        "Терапия",
        R.drawable.therapy,
        Screen.THERAPY.name
    )

    data object TherapyContent : NavigationItem(
        title = "Терапия",
        route = Screen.THERAPY_CONTENT.name
    )

    data object Diary : NavigationItem(
        title = "Дневник настроения",
        route = Screen.DIARY.name
    )

    data object DiaryMain : NavigationItem(
        title = "Главная дневника настроения",
        route = Screen.DIARY_MOOD.name
    )

    data object  AddMood: NavigationItem(
        title = "Добавить настроение",
        route = Screen.ADD_MOOD.name
    )

    data object EditMood : NavigationItem(
        title = "Редактировать запись о настроении",
        route =  "${Screen.EDIT_MOOD.name}/{obj_mood}"
    ) {
        fun getRouteWithArgs(mood: Mood) : String {
            val moodJson = Gson().toJson(mood)
            return "${Screen.EDIT_MOOD.name}/${moodJson.encode()}"
        }
    }

    data object  Homework: NavigationItem(
        title = "Домашняя работа",
        route = Screen.HOMEWORK.name
    )

    data object  MainHomework: NavigationItem(
        title = "Главная домашняя работа",
        route = Screen.MAIN_HOMEWORK.name
    )

    data object  AddHomework: NavigationItem(
        title = "Создать домашнюю работа",
        route = Screen.ADD_HOMEWORK.name
    )

    data object  StatisticHomework: NavigationItem(
        title = "Посмотреть статистику по домашней работе",
        route = Screen.STATISTIC_HOMEWORK.name
    )

    data object  BeforePracticeHomework: NavigationItem(
        title = "Отметить состояние до выполнения практике по домашней работе",
        route = Screen.BEFORE_PRACTICE_HOMEWORK.name
    )

    data object  PracticeHomework: NavigationItem(
        title = "Выполнять практику по домашней работе",
        route = Screen.PRACTICE_CONTENT.name
    )

    data object  AfterPracticeHomework: NavigationItem(
        title = "Отметить состояние после прохождения практики по домашней работе",
        route = Screen.AFTER_PRACTICE_HOMEWORK.name
    )

    data object ListModules : NavigationItem(
        title = "Модули",
        route = Screen.MODULES_LIST.name
    )

    data object ModuleContent : NavigationItem(
        title = "Список статей",
        route =  "${Screen.MODULE_CONTENT.name}/{content_list}"
    ) {
        fun getRouteWithArgs(contentList: List<ModuleContentModel>) : String {
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