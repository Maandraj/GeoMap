package com.maandraj.geomap.features.map.data.domain.model.direction

data class Step(
    val endLocation: Location,
    val startLocation: Location,
    val polyline: Polyline
)