package com.diiser.player

import com.diiser.network.config.DeezerNetworkConfig
import com.diiser.network.repository.PlayerRepository
import com.diiser.utils.ProviderContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val playerModule = module {
    viewModel { PlayerViewModel(get(), ProviderContext()) }
    single { PlayerUseCase(get()) }
    single { PlayerRepository(get()) }
    single { DeezerNetworkConfig(get()) }
}