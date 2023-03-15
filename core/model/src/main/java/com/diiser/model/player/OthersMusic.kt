package com.diiser.model.player

import com.google.gson.annotations.SerializedName

data class OthersMusic(
    @SerializedName("data")
    val musicList: List<Music>,
    val total: Int
)