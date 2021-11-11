package com.maandraj.geomap.features.map.data.domain

import com.maandraj.geomap.features.map.data.api.CoordinatesRepo
import javax.inject.Inject

class CoordinatesInteractor @Inject constructor(
    private val coordinatesRepo: CoordinatesRepo
) {
    suspend fun getAllCoordinates() = coordinatesRepo.getAllCoordinates()
}