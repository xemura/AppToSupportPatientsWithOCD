package com.xenia.apptosupportpatientswithocd.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xenia.apptosupportpatientswithocd.navigation.AppNavGraph
import com.xenia.apptosupportpatientswithocd.navigation.NavigationItem
import com.xenia.apptosupportpatientswithocd.navigation.rememberNavigationState
import com.xenia.apptosupportpatientswithocd.presentation.auth_screen.AuthViewModel
import com.xenia.apptosupportpatientswithocd.presentation.bottom_navigation.GlassmorphicBottomNavigation
import com.xenia.apptosupportpatientswithocd.presentation.main_screen.MainScreen
import com.xenia.apptosupportpatientswithocd.presentation.modules_screen.ContentTextScreen
import com.xenia.apptosupportpatientswithocd.presentation.modules_screen.ModuleContentScreen
import com.xenia.apptosupportpatientswithocd.presentation.modules_screen.ModulesScreen
import com.xenia.apptosupportpatientswithocd.presentation.profile_screen.ProfileScreen
import com.xenia.apptosupportpatientswithocd.presentation.scripts_screen.AddScriptScreen
import com.xenia.apptosupportpatientswithocd.presentation.scripts_screen.ScriptsScreen
import com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.TherapyScreen
import com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.diary_screens.AddMoodScreen
import com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.diary_screens.DiaryMainScreen
import com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.homework_screens.HomeworkScreen
import dev.chrisbanes.haze.HazeState

@Composable
fun EnterMainScreen() {
    val navigationState = rememberNavigationState()

    val hazeState = remember { HazeState() }

    val tabs = listOf(
        NavigationItem.Modules,
        NavigationItem.Scripts,
        NavigationItem.Main,
        NavigationItem.Therapy,
        NavigationItem.Profile
    )

    val component = getApplicationComponent()
    val viewModel: AuthViewModel = viewModel(factory = component.getViewModelFactory())

    Scaffold(
        bottomBar = {
            GlassmorphicBottomNavigation(
                navigationState,
                hazeState,
                tabs
            )
        }
    ) { contentPadding ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            mainScreenContent = {
                MainScreen(
                    contentPadding,
                    onModulesButtonPressed = {
                        navigationState.navigateTo(NavigationItem.Modules.route)
                    },
                    onScriptsButtonPressed = {
                        navigationState.navigateTo(NavigationItem.Scripts.route)
                    },
                    onTherapyButtonPressed = {
                        navigationState.navigateTo(NavigationItem.Therapy.route)
                    }
                )
            },
            modulesScreenContent = {
                ModulesScreen(
                    onModuleImageClickListener = {
                        navigationState.navigateToContentModule(it)
                    }
                )
            },
            moduleContentScreenContent = { contentList ->
                ModuleContentScreen(
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    },
                    contentList = contentList,
                    onArticleClickListener = {
                        navigationState.navigateToContentText(it)
                    }
                )
            },
            contentTextScreenContent = { text ->
                ContentTextScreen(
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    },
                    text
                )
            },
            profileScreenContent = {
                ProfileScreen(
                    onSaveButtonPressed = {
                        navigationState.navigateTo(NavigationItem.Main.route)
                    },
                    viewModel
                )
            },
            scriptsContentScreen = {
                ScriptsScreen(
                    onFloatingActionButtonClick = {
                        navigationState.navigateTo(NavigationItem.AddScript.route)
                    }
                )
            },
            addScriptScreen = {
                AddScriptScreen(
                    onBackPressed = {
                        navigationState.navigateTo(NavigationItem.Scripts.route)
                    },
                    onAddPressed = {
                        navigationState.navigateTo(NavigationItem.Scripts.route)
                    }
                    // еще надо добавить редактирование
                )
            },
            therapyScreenContent = {
                TherapyScreen(
                    onDiaryMoodCardPress = {
                        navigationState.navigateTo(NavigationItem.Diary.route)
                    },
                    onHomeworkCardPress = {
                        navigationState.navigateTo(NavigationItem.Homework.route)
                    }
                )
            },
            diaryMoodScreenContent = {
                DiaryMainScreen(
                    onBackPressed = {
                        navigationState.navigateTo(NavigationItem.Therapy.route)
                    },
                    onEditPressed = {

                    },
                    onAddPressed = {
                        navigationState.navigateTo(NavigationItem.AddMood.route)
                    }
                )
            },
            addMoodScreenContent = {
                AddMoodScreen(
                    onBackPressed = {
                        navigationState.navigateTo(NavigationItem.Diary.route)
                    },
                    onSavePressed = {
                        navigationState.navigateTo(NavigationItem.Diary.route)
                    }
                )
            },
            homeworkScreenContent = {
                HomeworkScreen(
                    onBackPressed = {
                        navigationState.navigateTo(NavigationItem.Therapy.route)
                    },
                    onAddPressed = {

                    },
                    onEditPressed = {

                    },
                    onPracticePressed = {

                    },
                    onStatisticPressed = {

                    }
                )
            }
        )
    }
}

