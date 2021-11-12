package com.maandraj.geomap.features.map.di

import com.maandraj.geomap.features.map.data.api.MapApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class MapFeatureModule {
    @Provides
    fun provideWaadsuApi(retrofit: Retrofit) =
        retrofit.create(MapApi::class.java)
}