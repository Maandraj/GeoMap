package com.maandraj.geomap.features.map.data.api.model.mapper

import com.maandraj.geomap.features.map.data.api.model.direction.DirectionRes
import com.maandraj.geomap.features.map.data.domain.model.direction.Direction
import javax.inject.Inject

class DirectionMapper @Inject constructor(
    private val routeMapper: RouteMapper,
) {
    fun map(res: DirectionRes): Direction = Direction(res.routesRes.map { routeMapper.map(it) })
}
