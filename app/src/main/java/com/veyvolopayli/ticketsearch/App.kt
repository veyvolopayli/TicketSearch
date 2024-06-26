package com.veyvolopayli.ticketsearch

import android.app.Application
import com.veyvolopayli.ticketsearch.di.dataModule
import com.veyvolopayli.ticketsearch.di.domainModule
import com.veyvolopayli.ticketsearch.di.presentationModule
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
            modules(dataModule, domainModule, presentationModule)
        }
    }
}