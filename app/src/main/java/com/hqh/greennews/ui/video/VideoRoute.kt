package com.hqh.greennews.ui.video

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable

@Composable
fun VideoRoute(
//    interestsViewModel: InterestsViewModel,
    isExpandedScreen: Boolean,
    scaffoldState: ScaffoldState = rememberScaffoldState()
){
    VideoScreen()
}