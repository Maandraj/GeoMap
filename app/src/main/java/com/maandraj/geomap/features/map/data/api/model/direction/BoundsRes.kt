package com.maandraj.geomap.features.map.data.api.model.direction


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BoundsRes(
    @Json(name = "northeast")
    val northeastRes: NortheastRes,
    @Json(name = "southwest")
    val southwestRes: SouthwestRes
)