package com.maandraj.geomap.features.map.data.api.model.mapper

import com.maandraj.geomap.features.map.data.api.model.direction.EndLocationRes
import com.maandraj.geomap.features.map.data.api.model.direction.StartLocationRes
import com.maandraj.geomap.features.map.data.domain.model.direction.Location
import javax.inject.Inject

class LocationMapper @Inject constructor(
) {
    fun mapStart(res: StartLocationRes): Location = Location(lat = res.latRes, lng = res.lngRes)
    fun mapEnd(res: EndLocationRes): Location = Location(lat = res.latRes, lng = res.lngRes)
}
