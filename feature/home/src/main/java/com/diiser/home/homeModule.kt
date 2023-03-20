package com.diiser.home

import com.diiser.network.config.NetworkConfig
import com.diiser.network.repository.HomeRepository
import com.diiser.utils.ProviderContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get(), ProviderContext()) }
    single { HomeUseCase(get()) }
    single { HomeRepository(get()) }
    single { NetworkConfig(get()) }
}