package com.adhishlal.mygithubprs.domain.base

import android.app.Application
import android.content.Context

abstract class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        context = this
    }

    companion object {
        lateinit var appContext: Context
            private set
        lateinit var context: Application
            private set
    }
}
