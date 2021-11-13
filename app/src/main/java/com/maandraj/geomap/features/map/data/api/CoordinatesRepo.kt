package com.maandraj.geomap.features.map.data.api

import com.maandraj.geomap.features.map.data.api.model.mapper.CoordinatesMapper
import com.maandraj.geomap.features.map.data.api.model.mapper.DirectionMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Query
import java.util.*
import javax.inject.Inject

class CoordinatesRepo @Inject constructor(
    private val coordinatesMapper: CoordinatesMapper,
    private val directionMapper: DirectionMapper,
    private val api: MapApi,
) {
    suspend fun getAllCoordinates() = withContext(Dispatchers.IO) {
        coordinatesMapper.map(api.getAllCoordinates())
    }

    suspend fun getDirections(
        origin: String,
        destination: String,
        language: String,
        mode: String ,
    ) = withContext(Dispatchers.IO) {
        directionMapper.map(api.getDirections(origin=origin, destination= destination,language= language, mode = mode))
    }
}