package com.diiser.application

import android.app.Application
import com.diiser.homeModule
import com.diiser.player.playerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class DiiserApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant( Timber.DebugTree())

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@DiiserApplication)
            modules(homeModule, playerModule)
        }

    }
}