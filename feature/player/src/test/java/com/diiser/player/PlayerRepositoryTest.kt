package com.diiser.player

import com.diiser.network.config.DeezerApi
import com.diiser.network.config.DeezerNetworkConfig
import com.diiser.network.config.NetworkConfig
import com.diiser.network.repository.PlayerRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PlayerRepositoryTest {

    private val deezerApi: DeezerNetworkConfig = mockk()

    private val playerRepository by lazy { PlayerRepository(deezerApi) }

    @Test
    fun shouldCallApiHomeData_whenCall_homeRepositoryHomeData() = runBlocking {

        runBlocking { playerRepository.getOthersMusicByArtist(123456789) }

        coVerify { deezerApi.apiService.getOthersMusicByArtist(123456789) }

    }
}