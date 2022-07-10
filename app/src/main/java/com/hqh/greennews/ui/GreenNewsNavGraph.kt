package com.hqh.greennews

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hqh.greennews.data.AppContainer
import com.hqh.greennews.ui.article.ArticleRoute
import com.hqh.greennews.ui.article.ArticleViewModel
import com.hqh.greennews.ui.video.VideoRoute

@Composable
fun GreenNewsNavGraph(
    appContainer: AppContainer,
    isExpandedScreen: Boolean,
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
            val articleViewModel: ArticleViewModel = viewModel(
                factory = ArticleViewModel.provideFactory(appContainer.postsRepository)
            )
            ArticleRoute(
                articleViewModel = articleViewModel,
                isExpandedScreen = isExpandedScreen
//                openDrawer = openDrawer
            )
        }
        composable(ScreensDestinations.VIDEO_ROUTE) {
//            val interestsViewModel: InterestsViewModel = viewModel(
//                factory = InterestsViewModel.provideFactory(appContainer.interestsRepository)
//            )
            VideoRoute(
                isExpandedScreen = isExpandedScreen,
            )
        }
    }
}