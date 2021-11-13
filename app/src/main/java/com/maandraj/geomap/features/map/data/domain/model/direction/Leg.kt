package com.maandraj.geomap.features.map.data.domain.model.direction

data class Leg(
    val distance: Properties,
    val duration: Properties,
    val endAddress: String,
    val startAddress: String,
    val steps: List<Step>
)