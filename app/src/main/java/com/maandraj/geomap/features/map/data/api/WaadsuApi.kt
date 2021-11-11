package com.maandraj.geomap.features.map.data.api

import com.maandraj.geomap.features.map.data.api.model.CoordinatesRes
import retrofit2.http.GET

interface WaadsuApi {
    @GET("russia.geo.json")
    suspend fun getAllCoordinates() : CoordinatesRes
}