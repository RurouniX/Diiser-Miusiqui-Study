package com.diiser.application

import android.app.Application
import com.diiser.home.homeModule
import com.diiser.player.playerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class InstrumentedTestApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@InstrumentedTestApplication)
            modules(homeModule, playerModule)
        }
    }
}