package com.diiser.network.repository

import com.diiser.network.config.NetworkConfig
import com.diiser.network.utils.ResultType
import com.diiser.network.utils.safeApiCall

class HomeRepository(private val api: NetworkConfig) {

    suspend fun getArtists(artistIdList: String) = safeApiCall(call = {
        val response = api.apiService.getArtistsAsync(artistIdList).await()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                ResultType.Success(response)
            } else
                ResultType.Error(response)
        } else
            ResultType.Error(response)
    })
}
