package com.maandraj.geomap.features.map.data.domain.model

import com.google.android.gms.maps.model.LatLng

/**
 * holes - коррдинаты для рисование внутренних контуров
 * coordinates - коррдинаты для рисование внешних контуров если это требуется, пригодится когда одни многоугольники располагаются внутри других
 */
data class Polygon(
    val holes: List<Hole>,
    val coordinates: List<LatLng>? = null,
)