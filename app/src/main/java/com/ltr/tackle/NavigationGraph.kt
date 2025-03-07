package com.ltr.tackle

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ltr.tackle.Screens.CreateEditTask.CreateEditScreen
import com.ltr.tackle.Screens.Collection.CollectionScreen
import com.ltr.tackle.Screens.Home.HomeScreen
import com.ltr.tackle.Screens.Settings.SettingsScreen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Home.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ) {
        composable(NavigationItem.Home.route) {
            HomeScreen(
                navController,
                { navController.navigate(SubScreen.CreateEditTask.route) }
            )
        }
        composable(NavigationItem.Collection.route) {
            CollectionScreen(navController)
        }
        composable(NavigationItem.Settings.route) {
            SettingsScreen(navController)
        }
        composable(
            SubScreen.CreateEditTask.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, tween(250)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Up, tween(250)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Down, tween(250)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down, tween(250)) },

        ) {
            CreateEditScreen(
                navController
            )
        }
    }
}