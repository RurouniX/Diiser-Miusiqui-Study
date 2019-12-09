package com.diiser.model.search


import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("cover")
    val cover: String = "", // https://api.deezer.com/album/86933012/image
    @SerializedName("cover_big")
    val coverBig: String = "", // https://cdns-images.dzcdn.net/images/cover/2d24239c39f349dc4439661ede023cd3/500x500-000000-80-0-0.jpg
    @SerializedName("cover_medium")
    val coverMedium: String = "", // https://cdns-images.dzcdn.net/images/cover/2d24239c39f349dc4439661ede023cd3/250x250-000000-80-0-0.jpg
    @SerializedName("cover_small")
    val coverSmall: String = "", // https://cdns-images.dzcdn.net/images/cover/2d24239c39f349dc4439661ede023cd3/56x56-000000-80-0-0.jpg
    @SerializedName("cover_xl")
    val coverXl: String = "", // https://cdns-images.dzcdn.net/images/cover/2d24239c39f349dc4439661ede023cd3/1000x1000-000000-80-0-0.jpg
    @SerializedName("id")
    val id: Int = 0, // 86933012
    @SerializedName("title")
    val title: String = "", // ROSENROT
    @SerializedName("tracklist")
    val tracklist: String = "", // https://api.deezer.com/album/86933012/tracks
    @SerializedName("type")
    val type: String = "" // album
)