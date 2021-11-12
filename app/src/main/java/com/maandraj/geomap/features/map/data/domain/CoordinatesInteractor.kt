package com.maandraj.geomap.features.map.data.domain

import com.maandraj.geomap.features.map.data.api.CoordinatesRepo
import java.util.*
import javax.inject.Inject

class CoordinatesInteractor @Inject constructor(
    private val coordinatesRepo: CoordinatesRepo
) {
    suspend fun getAllCoordinates() = coordinatesRepo.getAllCoordinates()
    suspend fun getDirection(origin: String,
                               destination: String,
                               language: String ,
                               mode: String) = coordinatesRepo.getDirections(origin=origin, destination= destination,language= language, mode = mode)

}
