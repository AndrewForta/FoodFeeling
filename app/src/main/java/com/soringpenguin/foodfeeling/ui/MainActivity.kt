package com.soringpenguin.foodfeeling.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.soringpenguin.foodfeeling.ui.navigation.Screen
import com.soringpenguin.foodfeeling.ui.theme.FoodFeelingTheme
import com.soringpenguin.ui_feelingentry.ui.FeelingEntry
import com.soringpenguin.ui_feelingentry.ui.FeelingListViewModel
import com.soringpenguin.ui_foodentry.ui.FoodEntry
import com.soringpenguin.ui_foodentry.ui.FoodListViewModel
import com.soringpenguin.ui_history.ui.HistoryLog
import com.soringpenguin.ui_history.ui.HistoryListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            FoodFeelingTheme {
                val items = listOf(
                    Screen.FeelingEntry,
                    Screen.FoodEntry,
                    Screen.History
                )
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            items.forEach { screen ->
                                BottomNavigationItem(
                                    icon = { Icon(screen.icon, contentDescription = null) },
                                    label = { Text(screen.label) },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            // Pop up to the start destination of the graph to
                                            // avoid building up a large stack of destinations
                                            // on the back stack as users select items
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            // Avoid multiple copies of the same destination when
                                            // reselecting the same item
                                            launchSingleTop = true
                                            // Restore state when reselecting a previously selected item
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(navController, startDestination = Screen.FeelingEntry.route, Modifier.padding(innerPadding)) {
                        composable(
                            route = Screen.FeelingEntry.route
                        ) {
                            val viewModel: FeelingListViewModel by viewModels()
                            FeelingEntry(
                                state = viewModel.state.value,
                                events = viewModel::onTriggerEvent,
                            )
                        }
                        composable(
                            route = Screen.FoodEntry.route
                        ) {
                            val viewModel: FoodListViewModel by viewModels()
                            FoodEntry(
                                state = viewModel.state.value,
                                events = viewModel::onTriggerEvent,
                            )
                        }
                        composable(
                            route = Screen.History.route
                        ) {
                            val viewModel: HistoryListViewModel by viewModels()
                            HistoryLog(
                                state = viewModel.state.value,
                                events = viewModel::onTriggerEvent,
                            )
                        }
                    }
                }
            }
        }
    }
}
