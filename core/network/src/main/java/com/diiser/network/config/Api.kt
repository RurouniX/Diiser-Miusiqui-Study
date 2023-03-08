package com.diiser.network.config

import com.diiser.model.search.SearchResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Objects

interface Api {
    @GET("search")
    fun getArtistsAsync(@Query("q") searchQuery: String): SearchResponse

    @GET("search")
    suspend fun getHomeData(@Query("q") searchQuery: String): Any

}