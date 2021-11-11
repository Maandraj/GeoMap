package com.maandraj.geomap.features.map.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoordinatesRes(
    @Json(name = "features")
    val featuresRes: List<FeatureRes>,
    @Json(name = "type")
    val typeRes: String
)