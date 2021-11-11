package com.maandraj.geomap.features.map.data.api.model.mapper

import com.maandraj.geomap.features.map.data.api.model.CoordinatesRes
import com.maandraj.geomap.features.map.data.domain.model.Coordinates
import javax.inject.Inject

class CoordinatesMapper @Inject constructor(
    private val featureMapper: FeatureMapper,
) {
    fun map(res: CoordinatesRes): Coordinates =
        Coordinates(type = res.typeRes,
            features = res.featuresRes.map { featureRes -> featureMapper.map(featureRes) })
}
