package com.hqh.greennews.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.view.WindowCompat
import com.hqh.greennews.GreenNewsApp
import com.hqh.greennews.GreenNewsApplication

class MainActivity : AppCompatActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

//        val appContainer = (application as GreenNewsApplication).container
        setContent {
            val widthSize.Class = calculateWindowSizeClass(this).widthSizeClass
//            GreenNewsApp(appContainer, widthSizeClass)
            Text(
                text = "heelo"
            )
        }
    }
}