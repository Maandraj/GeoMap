package com.maandraj.geomap.features.map.data.api.model.mapper

import com.maandraj.geomap.features.map.data.api.model.direction.DirectionRes
import com.maandraj.geomap.features.map.data.api.model.direction.PolylineRes
import com.maandraj.geomap.features.map.data.api.model.direction.RouteRes
import com.maandraj.geomap.features.map.data.domain.model.Coordinates
import com.maandraj.geomap.features.map.data.domain.model.direction.Direction
import com.maandraj.geomap.features.map.data.domain.model.direction.Polyline
import com.maandraj.geomap.features.map.data.domain.model.direction.Route
import javax.inject.Inject

class PolylineMapper @Inject constructor(
) {
    fun map(res: PolylineRes): Polyline  = Polyline(points = res.pointsRes)
}
