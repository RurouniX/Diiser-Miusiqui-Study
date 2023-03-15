package com.diiser.model.home

import com.diiser.model.player.Music
import com.google.gson.annotations.SerializedName

data class SearchModel(
    @SerializedName("data")
    val data: List<Music>,
    val next: String,
    val total: Int
)