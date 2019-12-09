package com.diiser.model.home

import com.google.gson.annotations.SerializedName

data class ArtistResponse(
    @SerializedName("resultCount")
    val resultCount: Int = 0, // 12
    @SerializedName("results")
    val artists: List<Artist> = listOf()
)