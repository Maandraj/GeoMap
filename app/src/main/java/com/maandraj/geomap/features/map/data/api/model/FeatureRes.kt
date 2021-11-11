package com.maandraj.geomap.features.map.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeatureRes(
    @Json(name = "geometry")
    val geometryRes: GeometryRes,
    @Json(name = "properties")
    val propertiesRes: PropertiesRes,
    @Json(name = "type")
    val typeRes: String,
)