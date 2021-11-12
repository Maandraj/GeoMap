package com.maandraj.geomap.features.map.data.api.model.direction


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LegRes(
    @Json(name = "distance")
    val distanceRes: DistanceRes,
    @Json(name = "duration")
    val durationRes: DurationRes,
    @Json(name = "end_address")
    val endAddressRes: String,
    @Json(name = "end_location")
    val endLocationRes: EndLocationRes,
    @Json(name = "start_address")
    val startAddressRes: String,
    @Json(name = "start_location")
    val startLocationRes: StartLocationRes,
    @Json(name = "steps")
    val stepsRes: List<StepRes>,
    @Json(name = "traffic_speed_entry")
    val trafficSpeedEntryRes: List<Any>,
    @Json(name = "via_waypoint")
    val viaWaypointRes: List<Any>
)