package com.maandraj.geomap.features.map.data.api.model.mapper

import com.maandraj.geomap.features.map.data.api.model.direction.DirectionRes
import com.maandraj.geomap.features.map.data.api.model.direction.DistanceRes
import com.maandraj.geomap.features.map.data.api.model.direction.DurationRes
import com.maandraj.geomap.features.map.data.api.model.direction.RouteRes
import com.maandraj.geomap.features.map.data.domain.model.Coordinates
import com.maandraj.geomap.features.map.data.domain.model.direction.Direction
import com.maandraj.geomap.features.map.data.domain.model.direction.Properties
import com.maandraj.geomap.features.map.data.domain.model.direction.Route
import javax.inject.Inject

class PropertiesMapper @Inject constructor(
) {
    fun mapDistance(res: DistanceRes): Properties =
        Properties(text = res.textRes, value = res.valueRes)
    fun mapDuration(res: DurationRes): Properties =
        Properties(text = res.textRes, value = res.valueRes)
}
