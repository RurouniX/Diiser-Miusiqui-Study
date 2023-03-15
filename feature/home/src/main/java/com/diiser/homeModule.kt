package com.diiser

import com.diiser.home.HomeUseCase
import com.diiser.home.HomeViewModel
import com.diiser.network.config.NetworkConfig
import com.diiser.network.repository.HomeRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get()) }
    single { HomeUseCase(get()) }
    single { HomeRepository(get()) }
    single { NetworkConfig(get()) }
}