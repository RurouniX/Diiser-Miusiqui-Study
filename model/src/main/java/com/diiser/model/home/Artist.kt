package com.diiser.model.home


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artist(
    @SerializedName("artistId")
    val artistId: Int = 0, // 1171421960
    @SerializedName("artistName")
    val artistName: String = "", // Charlie and the Chewie Humans
    @SerializedName("artistType")
    val artistType: String = "", // Artist
    @SerializedName("artworkUrl")
    val artworkUrl: String = "", // https://firebasestorage.googleapis.com/v0/b/tw-exercicio-mobile.appspot.com/o/albums%2Fcharlie-and-the-chewy-humans-epithet.png?alt=media&token=6a1baa1d-dfe1-4cb4-8042-b11de7b26dce
    @SerializedName("collectionId")
    val collectionId: Int = 0, // 879273553
    @SerializedName("collectionName")
    val collectionName: String = "", // Epithet
    @SerializedName("country")
    val country: String = "", // USA
    @SerializedName("id")
    val id: Int = 0, // 579273554
    @SerializedName("primaryGenreName")
    val primaryGenreName: String = "", // Metal
    @SerializedName("releaseDate")
    val releaseDate: String = "", // 2010-07-01T08:00:00Z
    @SerializedName("trackCensoredName")
    val trackCensoredName: String = "", // Riot!
    @SerializedName("trackExplicitness")
    val trackExplicitness: String = "", // explicit
    @SerializedName("trackName")
    val trackName: String = "", // Riot!
    @SerializedName("trackTimeMillis")
    val trackTimeMillis: Int = 0, // 207679
    @SerializedName("wrapperType")
    val wrapperType: String = "" // track
) : Parcelable