package com.maandraj.geomap.features.map.data.api.model.mapper

import com.maandraj.geomap.features.map.data.api.model.direction.DirectionRes
import com.maandraj.geomap.features.map.data.api.model.direction.LegRes
import com.maandraj.geomap.features.map.data.domain.model.Coordinates
import com.maandraj.geomap.features.map.data.domain.model.direction.Direction
import com.maandraj.geomap.features.map.data.domain.model.direction.Leg
import javax.inject.Inject

class LegMapper @Inject constructor(
    private val stepMapper: StepMapper,
    private val propertiesMapper: PropertiesMapper,
) {
    fun map(res: LegRes): Leg = Leg(
        steps = res.stepsRes.map { stepMapper.map(it) },
        endAddress = res.endAddressRes,
        startAddress = res.startAddressRes,
        distance = propertiesMapper.mapDistance(res.distanceRes),
        duration = propertiesMapper.mapDuration(res.durationRes))
}
