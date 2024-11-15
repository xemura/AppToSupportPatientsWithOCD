package com.xenia.apptosupportpatientswithocd.presentation.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xenia.apptosupportpatientswithocd.navigation.AppNavGraph
import com.xenia.apptosupportpatientswithocd.navigation.NavigationItem
import com.xenia.apptosupportpatientswithocd.navigation.rememberNavigationState
import com.xenia.apptosupportpatientswithocd.presentation.viewmodels.AuthViewModel
import com.xenia.apptosupportpatientswithocd.presentation.bottom_navigation.GlassmorphicBottomNavigation
import com.xenia.apptosupportpatientswithocd.presentation.getApplicationComponent
import com.xenia.apptosupportpatientswithocd.presentation.screens.main_screen.MainScreen
import com.xenia.apptosupportpatientswithocd.presentation.screens.modules_screen.ContentTextScreen
import com.xenia.apptosupportpatientswithocd.presentation.screens.modules_screen.ModuleContentScreen
import com.xenia.apptosupportpatientswithocd.presentation.screens.modules_screen.ModulesScreen
import com.xenia.apptosupportpatientswithocd.presentation.screens.profile_screen.ProfileScreenContent
import com.xenia.apptosupportpatientswithocd.presentation.viewmodels.ProfileViewModel
import com.xenia.apptosupportpatientswithocd.presentation.screens.scripts_screen.AddScriptScreen
import com.xenia.apptosupportpatientswithocd.presentation.viewmodels.ScriptViewModel
import com.xenia.apptosupportpatientswithocd.presentation.screens.scripts_screen.ScriptsScreenStateContent
import com.xenia.apptosupportpatientswithocd.presentation.screens.therapy_screen.TherapyScreen
import com.xenia.apptosupportpatientswithocd.presentation.screens.therapy_screen.diary_screens.AddMoodScreen
import com.xenia.apptosupportpatientswithocd.presentation.screens.therapy_screen.diary_screens.DiaryMoodMainContent
import com.xenia.apptosupportpatientswithocd.presentation.screens.therapy_screen.diary_screens.EditMoodScreen
import com.xenia.apptosupportpatientswithocd.presentation.screens.therapy_screen.diary_screens.MainAllMoodsListScreen
import com.xenia.apptosupportpatientswithocd.presentation.viewmodels.MoodViewModel
import com.xenia.apptosupportpatientswithocd.presentation.screens.therapy_screen.homework_screens.AddHomeworkScreen
import com.xenia.apptosupportpatientswithocd.presentation.screens.therapy_screen.homework_screens.EditHomeworkScreen
import com.xenia.apptosupportpatientswithocd.presentation.screens.therapy_screen.homework_screens.HomeworkScreenContentState
import com.xenia.apptosupportpatientswithocd.presentation.viewmodels.HomeworkViewModel
import com.xenia.apptosupportpatientswithocd.presentation.screens.therapy_screen.homework_screens.StatisticHomeworkScreen
import com.xenia.apptosupportpatientswithocd.presentation.screens.therapy_screen.practice_screens.PracticeContentScreen
import com.xenia.apptosupportpatientswithocd.presentation.screens.therapy_screen.practice_screens.StateAfterPracticeScreen
import com.xenia.apptosupportpatientswithocd.presentation.screens.therapy_screen.practice_screens.StateBeforePracticeScreen
import com.xenia.apptosupportpatientswithocd.presentation.viewmodels.StatisticHomeworkViewModel
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
    val authViewModel: AuthViewModel = viewModel(factory = component.getViewModelFactory())
    val moodViewModel: MoodViewModel = viewModel(factory = component.getViewModelFactory())
    val homeworkViewModel: HomeworkViewModel = viewModel(factory = component.getViewModelFactory())
    val statisticHomeworkViewModel: StatisticHomeworkViewModel = viewModel(factory = component.getViewModelFactory())
    val scriptsViewModel: ScriptViewModel = viewModel(factory = component.getViewModelFactory())
    val profileViewModel: ProfileViewModel = viewModel(factory = component.getViewModelFactory())

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
                ProfileScreenContent(
                    onSaveButtonPressed = { name, switchState, time ->
                        profileViewModel.saveChanges(name, switchState, time)
                    },
                    onSignOutPressed = {
                        authViewModel.signOut()
                    },
                    authViewModel
                )
            },
            scriptsContentScreen = {
                ScriptsScreenStateContent(
                    onFloatingActionButtonClick = {
                        navigationState.navHostController.popBackStack()
                        navigationState.navigateTo(NavigationItem.AddScript.route)
                    }
                )
            },
            addScriptScreen = {
                AddScriptScreen(
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                        navigationState.navigateTo(NavigationItem.Scripts.route)
                    },
                    onAddPressed = { scriptName, list ->
                        scriptsViewModel.addScript(scriptName, list)
                        navigationState.navigateTo(NavigationItem.Scripts.route)
                    }
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
                DiaryMoodMainContent(
                    onBackPressed = {
                        navigationState.navigateTo(NavigationItem.Therapy.route)
                    },
                    onEditPressed = {
                        navigationState.navHostController.popBackStack()
                        navigationState.navigateToEditMood(it)
                    },
                    onAddPressed = {
                        navigationState.navigateTo(NavigationItem.AddMood.route)
                    },
                    onDeleteMood = { id ->
                        moodViewModel.deleteMood(id)
                    },
                    onListAllMoodPressed = {
                        navigationState.navigateTo(NavigationItem.AllMoods.route)
                    }
                )
            },
            addMoodScreenContent = {
                AddMoodScreen(
                    onBackPressed = {
                        navigationState.navigateTo(NavigationItem.Diary.route)
                    },
                    onSavePressed = { assessment, note ->
                        moodViewModel.saveMood(assessment, note)
                        navigationState.navigateTo(NavigationItem.Diary.route)
                    }
                )
            },
            editMoodScreenContent = { mood ->
                EditMoodScreen(
                    mood = mood,
                    onBackPressed = {
                        navigationState.navigateTo(NavigationItem.Diary.route)
                    },
                    onSavePressed = { id, assessment, note ->
                        moodViewModel.updateMood(id, assessment, note)
                        navigationState.navigateTo(NavigationItem.Diary.route)
                    }
                )
            },
            homeworkScreenContent = {
                HomeworkScreenContentState(
                    onBackPressed = {
                        navigationState.navigateTo(NavigationItem.Therapy.route)
                    },
                    onAddPressed = {
                        navigationState.navigateTo(NavigationItem.AddHomework.route)
                    },
                    onEditPressed = {
                        navigationState.navHostController.popBackStack()
                        navigationState.navigateToEditHomework(it)
                    },
                    onPracticePressed = { homework ->
                        navigationState.navigateToPracticeBefore(homework)
                    },
                    onStatisticPressed = { homework ->
                        navigationState.navigateToStatisticHomeworkContent(homework)
                    },
                    onDeleteSwiped = { id ->
                        homeworkViewModel.deleteHomework(id)
                    }
                )
            },
            addHomeworkScreenContent = {
                AddHomeworkScreen(
                    onBackPressed = {
                        navigationState.navigateTo(NavigationItem.Homework.route)
                    },
                    onSavePressed = { obsessionInfo, triggerInfo, adviceInfo ->
                        homeworkViewModel.addHomework(obsessionInfo, triggerInfo, adviceInfo)
                        navigationState.navigateTo(NavigationItem.Homework.route)
                    }
                )
            },
            statisticHomeworkScreenContent = { homework ->
                StatisticHomeworkScreen(
                    homework,
                    onBackPressed = {
                        navigationState.navigateTo(NavigationItem.Homework.route)
                    }
                )
            },
            stateBeforePracticeHomework = { homework ->
                StateBeforePracticeScreen(
                    homework = homework,
                    onBackPressed = {
                        navigationState.navigateTo(NavigationItem.Homework.route)
                    },
                    onNextButtonPressed = { it, statistic ->
                        navigationState.navigateToPracticeContent(it, statistic)
                    }
                )
            },
            practiceContentHomework = { homework, statistic ->
                PracticeContentScreen(
                    homework,
                    statistic,
                    onNextButtonPressed = {
                        navigationState.navigateToPracticeAfter(it)
                    }
                )
            },
            stateAfterPracticeHomework = { statistic ->
                StateAfterPracticeScreen(
                    statisticModel = statistic,
                    onNextButtonPressed = { statisticHomework ->
                        statisticHomeworkViewModel.setStatisticHomeworkByID(statisticHomework)
                        navigationState.navigateTo(NavigationItem.Homework.route)
                    }
                )
            },
            allMoodsScreenContent = {
                MainAllMoodsListScreen(
                    onBackPressed = {
                        navigationState.navigateTo(NavigationItem.Diary.route)
                    },
                    onEditPressed = {
                        navigationState.navHostController.popBackStack()
                        navigationState.navigateToEditMood(it)
                    },
                    onDeleteMood = { id ->
                        moodViewModel.deleteMood(id)
                    },
                )
            },
            editHomeworkScreenContent = { homework ->
                EditHomeworkScreen(
                    homework = homework,
                    onBackPressed = {
                        navigationState.navigateTo(NavigationItem.MainHomework.route)
                    },
                    onSavePressed = { id, obsessionInfo, triggerInfo, adviceInfo ->
                        homeworkViewModel.updateHomeworkById(
                            id,
                            obsessionInfo,
                            triggerInfo,
                            adviceInfo
                        )
                        navigationState.navigateTo(NavigationItem.MainHomework.route)
                    }
                )
            }
        )
    }
}

