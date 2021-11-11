package com.maandraj.geomap.features.map.presentation

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.google.maps.android.SphericalUtil
import com.maandraj.geomap.R
import com.maandraj.geomap.databinding.FragmentMapBinding
import com.maandraj.geomap.features.map.data.domain.model.Coordinates
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : Fragment(R.layout.fragment_map), OnMapReadyCallback,
    GoogleMap.OnPolygonClickListener {
    private val viewModel: MapViewModel by viewModels()
    private val viewBinding: FragmentMapBinding by viewBinding(FragmentMapBinding::bind)
    private lateinit var coordinates: Coordinates
    private lateinit var options: GoogleMapOptions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        options = GoogleMapOptions()
    }

    /**
     * Получает координаты из viewModel
     * Получает карту
     * Указываются настройки карты(вид, элементы управления, и тд)
     */
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadingState.observe(viewLifecycleOwner, {
            viewBinding.loading.root.visibility = if (it)
                View.VISIBLE
            else
                View.GONE


        })
        viewModel.distance.observe(viewLifecycleOwner, {
            viewBinding.tvDistance.text =
                "${activity?.getString(R.string.length)}: $it ${activity?.getString(R.string.meter_squared)}"
        })
        options.mapType(GoogleMap.MAP_TYPE_NORMAL).compassEnabled(true)
        viewModel.coordinates.observe(viewLifecycleOwner, {
            coordinates = it
            val mapFragment = childFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment?
            SupportMapFragment.newInstance(options)
            mapFragment?.getMapAsync(this)
        })


    }

    /**
     * Когда карта готова вычесляются все координаты, дистанция и рисуется маршрут
     */
    override fun onMapReady(p0: GoogleMap) {
        val polygons = coordinates.features[0].geometry.polygons
        Log.i("GMap", "Polygons size: ${polygons.size}")
        var distance: Double = 0.0
        val allCoordinates: MutableList<LatLng> = mutableListOf()
        for (_polygon in polygons) {
            val polygonOptions = PolygonOptions()
            for (_hole in _polygon.holes) {
                lateinit var start: LatLng
                lateinit var end: LatLng
                var holesDistance = 0.0
                start = _hole.coordinates[0]
                end =
                    _polygon.holes[_polygon.holes.size - 1].coordinates[_hole.coordinates.size - 2]
                //       holesDistance = SphericalUtil.computeDistanceBetween(start, end)
                allCoordinates.addAll(_hole.coordinates)
                if (_polygon.coordinates == null) {
                    polygonOptions.addAll(_hole.coordinates)
                    /// Log.i("GMap", "Distance: ${SphericalUtil.computeDistanceBetween(start, end)}")

                    //  distance += holesDistance
                } else {
                    start = _polygon.coordinates[0]
                    end = _polygon.coordinates[_polygon.coordinates.size - 1]

                    //     distance += SphericalUtil.computeDistanceBetween(start, end) + holesDistance
                    polygonOptions.addAll(_polygon.coordinates)
                    polygonOptions.addHole(_hole.coordinates)
                }
            }


            polygonOptions
                .strokeColor(Color.RED)
                .strokeWidth(5.0f)
            p0.addPolygon(polygonOptions)

        }
        distance = SphericalUtil.computeLength(allCoordinates)
        viewModel.setDistance(distance)
        Log.i("GMap", "Distance: $distance")

//        val zoom = coordinates.features[0].geometry.polygons[0].holes[0].coordinates[0]
//        p0.moveCamera(CameraUpdateFactory.newLatLngZoom(zoom, 1f))


    }


    override fun onPolygonClick(p0: Polygon) {

    }
}