package com.maandraj.geomap.features.map.data.api.model.mapper

import com.google.android.gms.maps.model.LatLng
import com.maandraj.geomap.features.map.data.api.model.GeometryRes
import com.maandraj.geomap.features.map.data.domain.model.Geometry
import com.maandraj.geomap.features.map.data.domain.model.Hole
import com.maandraj.geomap.features.map.data.domain.model.Polygon
import javax.inject.Inject

class GeometryMapper @Inject constructor(
) {
    /**
     * Вычеслние по координатом маршрут всех внешних и внутренних полигонов
     * Возвращает маршрут который прошёл через маппер в бизнес логику(domain)
     */
    fun map(res: GeometryRes): Geometry {
        var coordinates: MutableList<LatLng> = mutableListOf()
        var holes: MutableList<Hole> = mutableListOf()
        val polygons: MutableList<Polygon> = mutableListOf()
        res.coordinatesRes.forEach { polygon ->
            if (holes.isNotEmpty())
                polygons.add(Polygon(holes))
            holes = mutableListOf()

            polygon.forEach { _holes ->
                if (coordinates.isNotEmpty())
                    holes.add(Hole(coordinates))
                coordinates = mutableListOf()

                _holes.forEach { _latlon ->
                    val latLong = LatLng(_latlon[1], _latlon[0])
                    coordinates.add(latLong)
                }
            }
        }
        return Geometry(type = res.typeRes, polygons = polygons)
    }
}