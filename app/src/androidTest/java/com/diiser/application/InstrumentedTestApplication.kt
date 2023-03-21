package com.diiser.application

import android.app.Application
import com.diiser.home.HomeUseCase
import com.diiser.home.HomeViewModel
import com.diiser.network.config.DeezerNetworkConfig
import com.diiser.network.config.NetworkConfig
import com.diiser.network.repository.HomeRepository
import com.diiser.network.repository.PlayerRepository
import com.diiser.player.PlayerUseCase
import com.diiser.player.PlayerViewModel
import com.diiser.utils.ProviderContext
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class InstrumentedTestApplication : Application() {


    private val testPlayerModule = module {
        viewModel { PlayerViewModel(get(), ProviderContext()) }
        single { PlayerUseCase(get()) }
        single { PlayerRepository(get()) }
        single { DeezerNetworkConfig(this@InstrumentedTestApplication)}//, "http://127.0.0.1:3000") }
    }
    private val testHomeModule = module {
        viewModel { HomeViewModel(get(), ProviderContext()) }
        single { HomeUseCase(get()) }
        single { HomeRepository(get()) }
        single { NetworkConfig(this@InstrumentedTestApplication)}//, "http://127.0.0.1:3000") }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@InstrumentedTestApplication)
            modules(testHomeModule, testPlayerModule)
        }
    }

}
