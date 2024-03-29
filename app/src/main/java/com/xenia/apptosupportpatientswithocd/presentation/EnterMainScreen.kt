package com.xenia.apptosupportpatientswithocd.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.xenia.apptosupportpatientswithocd.navigation.AppNavGraph
import com.xenia.apptosupportpatientswithocd.navigation.NavigationItem
import com.xenia.apptosupportpatientswithocd.navigation.rememberNavigationState
import com.xenia.apptosupportpatientswithocd.presentation.bottom_navigation.GlassmorphicBottomNavigation
import com.xenia.apptosupportpatientswithocd.presentation.main_screen.MainScreen
import com.xenia.apptosupportpatientswithocd.presentation.modules_screen.ModulesScreen
import com.xenia.apptosupportpatientswithocd.presentation.profile_screen.ProfileScreen
import com.xenia.apptosupportpatientswithocd.presentation.scripts_screen.ScriptsScreen
import com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.TherapyScreen
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

    Scaffold(
        bottomBar = {

            GlassmorphicBottomNavigation(navigationState, hazeState, tabs)

//            NavigationBar {
//                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
//
//                tabs.forEach { item ->
//                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
//                        it.route == item.route
//                    } ?: false
//
//                    NavigationBarItem(
//                        selected = selected,
//                        onClick = {
//                            if (!selected) {
//                                navigationState.navigateTo(item.route)
//                            }
//                        },
//                        label = {
//                            Text(text = item.title)
//                        },
//                        icon = {
//                            Icon(
//                                imageVector = if (selected) item.iconSelected
//                                else item.iconUnselected, contentDescription = item.title
//                            )
//                        },
//                        colors = NavigationBarItemDefaults.colors(
//
//                        )
//                    )
//                }
//            }
        }
    ) { contentPadding ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            mainScreenContent = {
                MainScreen(
                    paddingValues = contentPadding,
                )
            },
            modulesScreenContent = {
                ModulesScreen()
            },
            profileScreenContent = {
                ProfileScreen()
            },
            scriptsScreenContent = {
                ScriptsScreen()
            },
            therapyScreenContent = {
                TherapyScreen()
            }
        )
    }
}

