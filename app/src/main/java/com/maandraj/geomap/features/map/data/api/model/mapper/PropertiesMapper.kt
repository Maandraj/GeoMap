package com.maandraj.geomap.features.map.data.api.model.mapper

import com.maandraj.geomap.features.map.data.api.model.direction.DistanceRes
import com.maandraj.geomap.features.map.data.api.model.direction.DurationRes
import com.maandraj.geomap.features.map.data.domain.model.direction.Properties
import javax.inject.Inject

class PropertiesMapper @Inject constructor(
) {
    fun mapDistance(res: DistanceRes): Properties =
        Properties(text = res.textRes, value = res.valueRes)
    fun mapDuration(res: DurationRes): Properties =
        Properties(text = res.textRes, value = res.valueRes)
}
