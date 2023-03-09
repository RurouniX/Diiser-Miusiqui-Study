package com.diiser.network.config

import com.diiser.model.home.SearchModel
import com.diiser.model.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("search")
    fun getArtistsAsync(@Query("q") searchQuery: String): SearchResponse

    @GET("search")
    suspend fun getHomeData(@Query("q") searchQuery: String): SearchModel

}