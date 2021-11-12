package com.maandraj.geomap.features.map.data.api.model.direction


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DirectionRes(
    @Json(name = "geocoded_waypoints")
    val geocodedWaypointsRes: List<GeocodedWaypointRes>,
    @Json(name = "routes")
    val routesRes: List<RouteRes>,
    @Json(name = "status")
    val statusRes: String
)