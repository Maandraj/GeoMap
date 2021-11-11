package com.maandraj.geomap.features.map.data.api

import com.maandraj.geomap.features.map.data.api.model.CoordinatesRes
import com.maandraj.geomap.features.map.data.api.model.mapper.CoordinatesMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoordinatesRepo @Inject constructor(
    private val coordinatesMapper: CoordinatesMapper,
    private val api: WaadsuApi
) {
    suspend fun getAllCoordinates()  = withContext(Dispatchers.IO) {
            coordinatesMapper.map(api.getAllCoordinates())
        }
}