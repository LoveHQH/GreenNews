package com.hqh.greennews

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hqh.greennews.ui.article.ArticleRoute
import com.hqh.greennews.ui.article.ArticleViewModel
import com.hqh.greennews.ui.video.VideoRoute

@Composable
fun GreenNewsNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ScreensDestinations.ARTICLE_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ScreensDestinations.ARTICLE_ROUTE) {
            ArticleRoute(hiltViewModel())
        }
        composable(ScreensDestinations.VIDEO_ROUTE) {
            VideoRoute()
        }
    }
}