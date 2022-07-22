package com.hqh.greennews

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hqh.greennews.ui.DefineScreens
import com.hqh.greennews.ui.theme.GreenNewsTheme
import com.hqh.greennews.ui.video.VideoScreen

@Composable
fun GreenNewsApp() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    GreenNewsTheme {
        VideoScreen()
        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = {
                GreenNewsBottomBar(navController)
            }
        )
        {
            GreenNewsNavGraph(
                navController = navController
            )
        }
    }
}

@Composable
fun GreenNewsBottomBar(navController: NavHostController) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        DefineScreens.screens.forEach { screen ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                    }
//                    viewModel.selectTab(tab.title)
                },
                label = {
                    Text(text = stringResource(id = screen.label))
                },
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = stringResource(id = screen.label)
                    )
                },
                alwaysShowLabel = false
            )
        }
    }
}