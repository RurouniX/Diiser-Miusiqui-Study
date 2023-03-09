package com.diiser.model.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val id: Int,
    val md5_image: String,
    val title: String,
    val tracklist: String,
    val type: String
) : Parcelable