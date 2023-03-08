package com.diiser

import com.diiser.network.config.NetworkConfig
import com.diiser.network.repository.HomeRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get(), get()) }
    single { HomeUseCase(get()) }
    single { HomeRepository(get()) }
    single { NetworkConfig(get()) }
}