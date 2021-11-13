package com.maandraj.geomap.features.map.data.api.model.mapper

import com.maandraj.geomap.features.map.data.api.model.direction.DirectionRes
import com.maandraj.geomap.features.map.data.api.model.direction.RouteRes
import com.maandraj.geomap.features.map.data.domain.model.Coordinates
import com.maandraj.geomap.features.map.data.domain.model.direction.Direction
import com.maandraj.geomap.features.map.data.domain.model.direction.Route
import javax.inject.Inject

class RouteMapper @Inject constructor(
    private val legMapper: LegMapper
) {
    fun map(res: RouteRes): Route  = Route(legs = res.legsRes.map { legMapper.map(it) })
}
