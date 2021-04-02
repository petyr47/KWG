package com.aneke.peter.kwg.startup

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        insertKoin()
    }

    private fun insertKoin() =
        startKoin {
            androidContext(this@App)
            modules(com.aneke.peter.kwg.di.modules)
        }

}