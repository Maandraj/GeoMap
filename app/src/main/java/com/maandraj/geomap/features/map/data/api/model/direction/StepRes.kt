package com.maandraj.geomap.features.map.data.api.model.direction


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StepRes(
    @Json(name = "distance")
    val distanceRes: DistanceRes,
    @Json(name = "duration")
    val durationRes: DurationRes,
    @Json(name = "end_location")
    val endLocationRes: EndLocationRes,
    @Json(name = "html_instructions")
    val htmlInstructionsRes: String,
    @Json(name = "polyline")
    val polylineRes: PolylineRes,
    @Json(name = "start_location")
    val startLocationRes: StartLocationRes,
    @Json(name = "travel_mode")
    val travelModeRes: String
)