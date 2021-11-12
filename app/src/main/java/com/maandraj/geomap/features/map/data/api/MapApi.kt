package com.maandraj.geomap.features.map.data.api

import com.maandraj.geomap.BuildConfig.MAPS_API_KEY
import com.maandraj.geomap.features.map.data.api.model.CoordinatesRes
import com.maandraj.geomap.features.map.data.api.model.direction.DirectionRes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface MapApi {
    @GET("https://waadsu.com/api/russia.geo.json")
    suspend fun getAllCoordinates(): CoordinatesRes

    @GET("directions/json?" +
            "&key=${MAPS_API_KEY}")
    suspend fun getDirections(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("language") language: String,
        @Query("mode") mode: String ,
    ): DirectionRes
}