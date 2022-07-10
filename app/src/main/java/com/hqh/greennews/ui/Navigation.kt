package com.hqh.greennews

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object ScreensDestinations {
    const val ARTICLE_ROUTE = "Article"
    const val VIDEO_ROUTE = "Video"
}

/**
 * Models the navigation actions in the app.
 */
class NavigationActions(navController: NavHostController) {
    val navigateToArticle: () -> Unit = {
        navController.navigate(ScreensDestinations.ARTICLE_ROUTE) {
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
    val navigateToVideo: () -> Unit = {
        navController.navigate(ScreensDestinations.VIDEO_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}