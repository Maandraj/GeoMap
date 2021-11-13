package com.maandraj.geomap.features.map.data.api.model.direction


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NortheastRes(
    @Json(name = "lat")
    val latRes: Double,
    @Json(name = "lng")
    val lngRes: Double
)