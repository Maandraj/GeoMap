package com.maandraj.geomap.features.map.data.api.model.direction


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RouteRes(
    @Json(name = "bounds")
    val boundsRes: BoundsRes,
    @Json(name = "copyrights")
    val copyrightsRes: String,
    @Json(name = "legs")
    val legsRes: List<LegRes>,
    @Json(name = "overview_polyline")
    val overviewPolylineRes: OverviewPolylineRes,
    @Json(name = "summary")
    val summaryRes: String,
    @Json(name = "warnings")
    val warningsRes: List<String>,
    @Json(name = "waypoint_order")
    val waypointOrderRes: List<Any>
)