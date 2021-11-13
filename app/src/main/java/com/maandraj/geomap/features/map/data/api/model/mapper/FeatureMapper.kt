package com.maandraj.geomap.features.map.data.api.model.mapper

import com.maandraj.geomap.features.map.data.api.model.FeatureRes
import com.maandraj.geomap.features.map.data.domain.model.Feature
import javax.inject.Inject

class FeatureMapper @Inject constructor(
    private val geometryMapper: GeometryMapper
) {
    fun map(res:FeatureRes) : Feature =
        Feature(type = res.typeRes, geometry = geometryMapper.map(res.geometryRes) )
}