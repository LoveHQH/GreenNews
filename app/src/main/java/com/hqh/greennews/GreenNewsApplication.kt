package com.hqh.greennews

import android.app.Application
import com.hqh.greennews.data.AppContainer
import com.hqh.greennews.data.AppContainerImpl

class GreenNewsApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}