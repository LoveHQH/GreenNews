package com.hqh.greennews.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.hqh.greennews.R

sealed class DefineScreens(
    val route: String,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
){
    companion object {
        val screens = listOf(
            Article,
            Video
        )

        const val route_article = "Article"
        const val route_video = "Video"
    }

    private object Article : DefineScreens(
        route_article,
        R.string.article_title,
        R.drawable.ic_newspaper
    )

    private object Video : DefineScreens(
        route_video,
        R.string.video_title,
        R.drawable.ic_youtube
    )
}