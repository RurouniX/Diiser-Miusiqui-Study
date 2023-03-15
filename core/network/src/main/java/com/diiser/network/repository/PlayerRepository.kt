package com.diiser.network.repository

import com.diiser.network.config.DeezerNetworkConfig
import com.diiser.network.utils.safeApiCall

class PlayerRepository(private val api: DeezerNetworkConfig) {

    suspend fun getOthersMusicByArtist(artistId: Int) = safeApiCall(call = {
        api.apiService.getOthersMusicBuArtist(artistId)
    })

}


