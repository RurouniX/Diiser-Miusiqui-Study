package com.diiser.model.home

import com.google.gson.annotations.SerializedName

data class SearchModel(
    @SerializedName("data")
    val dataModel: List<DataModel>,
    val next: String,
    val total: Int
)