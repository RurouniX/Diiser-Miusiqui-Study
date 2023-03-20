package com.diiser.network.config

import com.diiser.model.home.SearchModel
import com.diiser.model.player.OthersMusic
import com.diiser.model.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search")
    fun getArtistsAsync(@Query("q") searchQuery: String): SearchResponse

    @GET("search")
    suspend fun getHomeData(@Query("q") searchQuery: String): SearchModel
}


interface DeezerApi {
    @GET("artist/{artistID}/top?limit=50")
    suspend fun getOthersMusicByArtist(@Path("artistID") artistID: Int): OthersMusic

}