package com.diiser.model.search


import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("album")
    val album: Album = Album(),
    @SerializedName("artist")
    val artist: Artist = Artist(),
    @SerializedName("duration")
    val duration: Int = 0, // 226
    @SerializedName("explicit_content_cover")
    val explicitContentCover: Int = 0, // 2
    @SerializedName("explicit_content_lyrics")
    val explicitContentLyrics: Int = 0, // 0
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean = false, // false
    @SerializedName("id")
    val id: Int = 0, // 630594272
    @SerializedName("link")
    val link: String = "", // https://www.deezer.com/track/630594272
    @SerializedName("preview")
    val preview: String = "", // https://cdns-preview-7.dzcdn.net/stream/c-7687734ead1b5d8b3c292b588c7a5299-4.mp3
    @SerializedName("rank")
    val rank: Int = 0, // 646781
    @SerializedName("readable")
    val readable: Boolean = false, // true
    @SerializedName("title")
    val title: String = "", // BENZIN
    @SerializedName("title_short")
    val titleShort: String = "", // BENZIN
    @SerializedName("title_version")
    val titleVersion: String = "",
    @SerializedName("type")
    val type: String = "" // track
)