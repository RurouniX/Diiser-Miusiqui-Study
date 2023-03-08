package com.diiser.model.search


import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("id")
    val id: Int = 0, // 464
    @SerializedName("link")
    val link: String = "", // https://www.deezer.com/artist/464
    @SerializedName("name")
    val name: String = "", // Rammstein
    @SerializedName("picture")
    val picture: String = "", // https://api.deezer.com/artist/464/image
    @SerializedName("picture_big")
    val pictureBig: String = "", // https://cdns-images.dzcdn.net/images/artist/d05f4f98703d86e3f1c9a9f6711ea3f3/500x500-000000-80-0-0.jpg
    @SerializedName("picture_medium")
    val pictureMedium: String = "", // https://cdns-images.dzcdn.net/images/artist/d05f4f98703d86e3f1c9a9f6711ea3f3/250x250-000000-80-0-0.jpg
    @SerializedName("picture_small")
    val pictureSmall: String = "", // https://cdns-images.dzcdn.net/images/artist/d05f4f98703d86e3f1c9a9f6711ea3f3/56x56-000000-80-0-0.jpg
    @SerializedName("picture_xl")
    val pictureXl: String = "", // https://cdns-images.dzcdn.net/images/artist/d05f4f98703d86e3f1c9a9f6711ea3f3/1000x1000-000000-80-0-0.jpg
    @SerializedName("tracklist")
    val tracklist: String = "", // https://api.deezer.com/artist/464/top?limit=50
    @SerializedName("type")
    val type: String = "" // artist
)