package com.diiser.network.repository

import com.diiser.network.config.NetworkConfig
import com.diiser.network.utils.ResultType
import com.diiser.network.utils.safeApiCall

class HomeRepository(private val api: NetworkConfig) {

    suspend fun getArtists(artistIdList: String) = safeApiCall(call = {
        api.apiService.getArtistsAsync(artistIdList)
    })

    suspend fun getHomeData(search: String) = safeApiCall(call = {
        api.apiService.getHomeData(search)
    })

}


