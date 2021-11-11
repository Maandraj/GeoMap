package com.maandraj.geomap.features.map.data.domain.model

import com.google.android.gms.maps.model.LatLng


data class Geometry(
    val polygons :List<Polygon>,
    val type: String
)