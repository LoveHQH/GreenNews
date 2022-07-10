package com.hqh.greennews.ui.article

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import com.hqh.greennews.ui.theme.article.ArticleScreen

@Composable
fun ArticleRoute(
    articleViewModel: ArticleViewModel,
    isExpandedScreen: Boolean,
//    openDrawer: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val uiState by articleViewModel.uiState.collectAsState()

    ArticleRoute(
        uiState = uiState,
        isExpandedScreen = isExpandedScreen,
        onToggleFavorite = { articleViewModel.toggleFavourite(it) },
        onSelectPost = { articleViewModel.selectArticle(it) },
        onRefreshPosts = { articleViewModel.refreshPosts() },
        onErrorDismiss = { articleViewModel.errorShown(it) },
        onInteractWithFeed = { articleViewModel.interactedWithFeed() },
        onInteractWithArticleDetails = { articleViewModel.interactedWithArticleDetails(it) },
        onSearchInputChanged = { articleViewModel.onSearchInputChanged(it) },
//        openDrawer = openDrawer,
        scaffoldState = scaffoldState,
    )
}

/**
 * Displays the Article route.
 *
 * This composable is not coupled to any specific state management.
 *
 * @param uiState (state) the data to show on the screen
 * @param isExpandedScreen (state) whether the screen is expanded
 * @param onToggleFavorite (event) toggles favorite for a post
 * @param onSelectPost (event) indicate that a post was selected
 * @param onRefreshPosts (event) request a refresh of posts
 * @param onErrorDismiss (event) error message was shown
 * @param onInteractWithFeed (event) indicate that the feed was interacted with
 * @param onInteractWithArticleDetails (event) indicate that the article details were interacted
 * with
 * @param openDrawer (event) request opening the app drawer
 * @param scaffoldState (state) state for the [Scaffold] component on this screen
 */

@Composable
fun ArticleRoute(
    uiState: ArticleUiState,
    isExpandedScreen: Boolean,
    onToggleFavorite: (String) -> Unit,
    onSelectPost: (String) -> Unit,
    onRefreshPosts: () -> Unit,
    onErrorDismiss: (Long) -> Unit,
    onInteractWithFeed: () -> Unit,
    onInteractWithArticleDetails: (String) -> Unit,
    onSearchInputChanged: (String) -> Unit,
//    openDrawer: () -> Unit,
    scaffoldState: ScaffoldState
) {
    // Construct the lazy list states for the list and the details outside of deciding which one to
    // show. This allows the associated state to survive beyond that decision, and therefore
    // we get to preserve the scroll throughout any changes to the content.
    val homeListLazyListState = rememberLazyListState()
    val articleDetailLazyListStates = when (uiState) {
        is ArticleUiState.HasPosts -> uiState.postsFeed.allPosts
        is ArticleUiState.NoPosts -> emptyList()
    }.associate { post ->
        key(post.id) {
            post.id to rememberLazyListState()
        }
    }

    val homeArticleScreenType = getHomeArticleScreenType(isExpandedScreen, uiState)
    when (homeArticleScreenType) {
        HomeArticleScreenType.FeedWithArticleDetails -> {
            HomeFeedWithArticleDetailsScreen(
                uiState = uiState,
                showTopAppBar = !isExpandedScreen,
                onToggleFavorite = onToggleFavorite,
                onSelectPost = onSelectPost,
                onRefreshPosts = onRefreshPosts,
                onErrorDismiss = onErrorDismiss,
                onInteractWithList = onInteractWithFeed,
                onInteractWithDetail = onInteractWithArticleDetails,
//                openDrawer = openDrawer,
                homeListLazyListState = homeListLazyListState,
                articleDetailLazyListStates = articleDetailLazyListStates,
                scaffoldState = scaffoldState,
                onSearchInputChanged = onSearchInputChanged,
            )
        }
        HomeArticleScreenType.Feed -> {
            HomeFeedArticleScreen(
                uiState = uiState,
                showTopAppBar = !isExpandedScreen,
                onToggleFavorite = onToggleFavorite,
                onSelectPost = onSelectPost,
                onRefreshPosts = onRefreshPosts,
                onErrorDismiss = onErrorDismiss,
//                openDrawer = openDrawer,
                homeListLazyListState = homeListLazyListState,
                scaffoldState = scaffoldState,
                onSearchInputChanged = onSearchInputChanged,
            )
        }
        HomeArticleScreenType.ArticleDetails -> {
            // Guaranteed by above condition for home screen type
            check(uiState is ArticleUiState.HasPosts)

            ArticleScreen(
                post = uiState.selectedPost,
                isExpandedScreen = isExpandedScreen,
                onBack = onInteractWithFeed,
                isFavorite = uiState.favorites.contains(uiState.selectedPost.id),
                onToggleFavorite = {
                    onToggleFavorite(uiState.selectedPost.id)
                },
                lazyListState = articleDetailLazyListStates.getValue(
                    uiState.selectedPost.id
                )
            )
            // If we are just showing the detail, have a back press switch to the list.
            // This doesn't take anything more than notifying that we "interacted with the list"
            // since that is what drives the display of the feed
            BackHandler {
                onInteractWithFeed()
            }
        }
    }
}

/**
 * A precise enumeration of which type of screen to display at the home route.
 *
 * There are 3 options:
 * - [FeedWithArticleDetails], which displays both a list of all articles and a specific article.
 * - [Feed], which displays just the list of all articles
 * - [ArticleDetails], which displays just a specific article.
 */

private enum class HomeArticleScreenType {
    FeedWithArticleDetails,
    Feed,
    ArticleDetails
}

/**
 * Returns the current [HomeScreenType] to display, based on whether or not the screen is expanded
 * and the [HomeUiState].
 */
@Composable
private fun getHomeArticleScreenType(
    isExpandedScreen: Boolean,
    uiState: ArticleUiState
): HomeArticleScreenType = when (isExpandedScreen) {
    false -> {
        when (uiState) {
            is ArticleUiState.HasPosts -> {
                if (uiState.isArticleOpen) {
                    HomeArticleScreenType.ArticleDetails
                } else {
                    HomeArticleScreenType.Feed
                }
            }
            is ArticleUiState.NoPosts -> HomeArticleScreenType.Feed
        }
    }
    true -> HomeArticleScreenType.FeedWithArticleDetails
}