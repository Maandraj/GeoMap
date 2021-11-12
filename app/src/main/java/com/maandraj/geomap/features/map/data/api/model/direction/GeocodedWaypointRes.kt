package com.maandraj.geomap.features.map.data.api.model.direction


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GeocodedWaypointRes(
    @Json(name = "geocoder_status")
    val geocoderStatusRes: String,
    @Json(name = "place_id")
    val placeIdRes: String,
    @Json(name = "types")
    val typesRes: List<String>
)