package com.maandraj.geomap.features.map.data.api.model.mapper

import com.maandraj.geomap.features.map.data.api.model.direction.StepRes
import com.maandraj.geomap.features.map.data.domain.model.direction.Step
import javax.inject.Inject

class StepMapper @Inject constructor(
    private val locationMapper: LocationMapper,
    private val polylineMapper: PolylineMapper
) {
    fun map(res: StepRes): Step = Step(startLocation = locationMapper.mapStart(res.startLocationRes),
        endLocation = locationMapper.mapEnd(res.endLocationRes),
        polyline = polylineMapper.map(res.polylineRes))
}
