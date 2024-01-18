package com.staskokoc.unsplashdev.app

import android.app.Application
import com.staskokoc.unsplashdev.di.appModule
import com.staskokoc.unsplashdev.di.dataModule
import com.staskokoc.unsplashdev.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}