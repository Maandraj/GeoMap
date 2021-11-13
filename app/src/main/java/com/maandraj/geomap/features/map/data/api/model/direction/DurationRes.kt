package com.maandraj.geomap.features.map.data.api.model.direction


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DurationRes(
    @Json(name = "text")
    val textRes: String,
    @Json(name = "value")
    val valueRes: Int
)