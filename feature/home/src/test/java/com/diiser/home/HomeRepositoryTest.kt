package com.diiser.home

import com.diiser.network.config.Api
import com.diiser.network.config.NetworkConfig
import com.diiser.network.repository.HomeRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class HomeRepositoryTest {

    private val apiHome: NetworkConfig = mockk()

    private val homeRepository by lazy { HomeRepository(apiHome) }

    @Test
    fun shouldCallApiHomeData_whenCall_homeRepositoryHomeData() = runBlocking {

        runBlocking { homeRepository.getHomeData("Metal") }

        coVerify { apiHome.apiService.getHomeData("Metal") }

    }
}