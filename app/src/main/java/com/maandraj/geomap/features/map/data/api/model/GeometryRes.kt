package com.maandraj.geomap.features.map.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GeometryRes(
    @Json(name = "coordinates")
    val coordinatesRes: List<List<List<List<Double>>>>,
    @Json(name = "type")
    val typeRes: String
)