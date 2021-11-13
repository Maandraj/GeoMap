package com.maandraj.geomap.features.map.data.api.model.mapper

import com.maandraj.geomap.features.map.data.api.model.direction.DirectionRes
import com.maandraj.geomap.features.map.data.api.model.direction.EndLocationRes
import com.maandraj.geomap.features.map.data.api.model.direction.RouteRes
import com.maandraj.geomap.features.map.data.api.model.direction.StartLocationRes
import com.maandraj.geomap.features.map.data.domain.model.Coordinates
import com.maandraj.geomap.features.map.data.domain.model.direction.Direction
import com.maandraj.geomap.features.map.data.domain.model.direction.Location
import com.maandraj.geomap.features.map.data.domain.model.direction.Route
import javax.inject.Inject

class LocationMapper @Inject constructor(
) {
    fun mapStart(res: StartLocationRes): Location = Location(lat = res.latRes, lng = res.lngRes)
    fun mapEnd(res: EndLocationRes): Location = Location(lat = res.latRes, lng = res.lngRes)
}
