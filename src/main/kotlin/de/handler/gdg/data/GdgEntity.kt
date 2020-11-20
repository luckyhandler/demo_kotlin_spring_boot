package de.handler.gdg.data

import com.google.gson.annotations.SerializedName

data class GdgEntity(
    var id: String,
    @SerializedName("result_type")
    val resultType: ResultType,
    val picture: Picture,
    val title: String,
    val country: String,
    val url: String,
    val city: String
)