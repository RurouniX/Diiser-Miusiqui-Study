package com.diiser.model.search


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("data")
    val track: List<Track> = listOf(),
    @SerializedName("next")
    val next: String = "", // https://api.deezer.com/search?redirect_uri=http%253A%252F%252Fguardian.mashape.com%252Fcallback&q=rammstein&index=25
    @SerializedName("total")
    val total: Int = 0 // 300
)