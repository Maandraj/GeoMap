package com.maandraj.geomap.features.map.presentation

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.google.android.material.button.MaterialButton
import com.google.maps.android.SphericalUtil
import com.maandraj.geomap.R
import com.maandraj.geomap.databinding.FragmentMapBinding
import com.maandraj.geomap.features.map.data.domain.model.Coordinates
import com.maandraj.geomap.features.map.data.domain.model.Geometry
import com.maandraj.geomap.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.text.DecimalFormat
import java.util.*
import android.content.res.Resources

import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.PolyUtil
import com.maandraj.geomap.BuildConfig.MAPS_API_KEY
import okhttp3.OkHttpClient
import okhttp3.Request
import com.google.android.gms.maps.model.BitmapDescriptorFactory

import com.google.android.gms.maps.model.BitmapDescriptor

@AndroidEntryPoint
class MapFragment : Fragment(R.layout.fragment_map), GoogleMap.OnMarkerClickListener,
    OnMapReadyCallback {
    private val viewModel: MapViewModel by viewModels()
    private val viewBinding: FragmentMapBinding by viewBinding(FragmentMapBinding::bind)
    private lateinit var coordinates: Coordinates
    private lateinit var options: GoogleMapOptions
    private lateinit var map: GoogleMap
    private var addMarkerButton: MaterialButton? = null
    private var manualCoordinates: Boolean = true
    private lateinit var latLangBounds: LatLngBounds
    private var routePolyline: Polyline? = null
    private var systemGeometry: String = ""
    private var distanceText = ""
    private var typeRoute = "walking"
    private var startMarker: Marker? = null
    private var endMarker: Marker? = null


    /**
     * Получает координаты из viewModel
     * Установка нужных слушателей
     * Указываются настройки карты(вид, элементы управления)
     */
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        options = GoogleMapOptions()
        viewModel.alert.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        viewModel.loadingState.observe(viewLifecycleOwner, {
            viewBinding.loading.root.visibility = if (it)
                View.VISIBLE
            else
                View.GONE
        })
        with(viewBinding.btnClose) {
            setOnClickListener {
                hideKeyboard()
                viewBinding.otherSettingsGroup.visibility = View.GONE
                viewBinding.ivMenu.visibility = View.VISIBLE
            }
        }
        with(viewBinding.ivMenu) {
            setOnClickListener {
                it.visibility = View.GONE
                viewBinding.otherSettingsGroup.visibility = View.VISIBLE
            }
        }
        with(viewBinding.tgGroupLocation) {
            addOnButtonCheckedListener { group, checkedId, isChecked ->
                if (isChecked) {
                    manualCoordinates = false
                    when (checkedId) {
                        R.id.tg_lat_location ->
                            addMarkerButton = viewBinding.tgLatLocation

                        R.id.tg_lng_location ->
                            addMarkerButton = viewBinding.tgLngLocation
                    }
                } else
                    manualCoordinates = true
            }
        }
        with(viewBinding.tgGroupDirections) {
            addOnButtonCheckedListener { group, checkedId, isChecked ->
                if (isChecked) {
                    when (checkedId) {
                        R.id.tg_walking ->
                            typeRoute = "walking"

                        R.id.tg_driving ->
                            typeRoute = "driving"
                        R.id.tg_bicycling ->
                            typeRoute = "bicycling"
                        R.id.tg_transit ->
                            typeRoute = "transit"
                    }
                } else
                    manualCoordinates = true
            }
        }
        viewBinding.etFirstMarker.setOnEditorActionListener { v, actionId, event ->
            if (actionId != EditorInfo.IME_ACTION_SEND) {
                addMarkerButton = viewBinding.tgLatLocation
                manualTextCoordinates(v)
            } else {
                manualTextCoordinates(v)
            }
        }
        viewBinding.etSecondMarker.setOnEditorActionListener { v, actionId, event ->
            if (actionId != EditorInfo.IME_ACTION_SEND) {
                addMarkerButton = viewBinding.tgLngLocation
                manualTextCoordinates(v)

            } else {
                manualTextCoordinates(v)
            }
        }
        viewModel.distance.observe(viewLifecycleOwner, {
            viewBinding.tvDistance.visibility = View.VISIBLE

            if (distanceText.isNotEmpty())
                viewBinding.tvDistance.text =
                    "${activity?.getString(R.string.distance)}: $distanceText "
            else
                viewBinding.tvDistance.text =
                    "${activity?.getString(R.string.distance)}: $it $systemGeometry"
        })
        options.mapType(GoogleMap.MAP_TYPE_HYBRID)
        options.zoomControlsEnabled(false)
        viewModel.coordinates.observe(viewLifecycleOwner, {
            coordinates = it
            val mapFragment = childFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment?
            SupportMapFragment.newInstance(options)
            mapFragment?.getMapAsync(this)
        })
    }

    /**
     * Устанавливет стиль карте зависимости от темы устройства
     */
    private fun setStyleMap() {
        try {
            val success: Boolean = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    requireContext(), R.raw.style_map_json))
            if (!success) {
                Log.e("GMap", "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e("GMap", "Can't find style. Error: ", e)
        }
    }

    /**
     * Подготовка карты и камеры для рисование маршрутов
     */
    override fun onMapReady(p0: GoogleMap) {
        map = p0
        when (requireContext().resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> setStyleMap()
        }
        viewBinding.ivMenu.visibility = View.VISIBLE
        viewBinding.llPanel.visibility = View.VISIBLE
        with(viewBinding.btnCoordinates) {
            visibility = View.VISIBLE
            setOnClickListener {
                drawPolygon()
            }
        }
        map.setOnMapClickListener {
            viewBinding.btnCoordinates.visibility = View.VISIBLE
            if (!manualCoordinates)
                addMarker(it)
        }
        viewBinding.ivZoomOut.setOnClickListener {
            map.moveCamera(CameraUpdateFactory.zoomTo(1.0f))
        }
        viewBinding.ivZoomOutLoupe.setOnClickListener {
            map.moveCamera(CameraUpdateFactory.zoomOut())
        }
        viewBinding.ivZoomInLoupe.setOnClickListener {
            map.moveCamera(CameraUpdateFactory.zoomIn())
        }
        viewBinding.btnRoute.setOnClickListener {
            drawRoute()
        }
    }

    /**
     * Установка и валидация координат которые ввел пользователь
     */
    @SuppressLint("SetTextI18n")
    private fun manualTextCoordinates(v: TextView): Boolean {
        val indexComma = v.text.indexOf(",")
        val indexPoint = v.text.indexOf(".")
        if (indexComma != -1 && indexComma + 1 != indexPoint && indexComma - 1 != indexPoint) {
            return if (v.text.toString().substringBefore(",").isNotEmpty() && v.text.toString()
                    .substringAfter(",")
                    .isNotEmpty()
            ) {
                val lat = v.text.toString().substringBefore(",").toDouble()
                val lng = v.text.toString().substringAfter(",").toDouble()
                val latLng = LatLng(lat, lng)
                v.text = "${latLng.latitude},${latLng.longitude}"
                addMarker(latLng)
                false
            } else {
                viewModel.setAlert(getString(R.string.invalid_coordinates))
                true
            }
        } else {
            viewModel.setAlert(getString(R.string.invalid_coordinates))
            return true

        }
    }

    /**
     * Установка маркеров для постороения дальнейшего маршрута
     */
    @SuppressLint("SetTextI18n", "ResourceType")
    private fun addMarker(latLng: LatLng) {

        var addresses: List<Address> = listOf()
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
        map.setOnMarkerClickListener(this)
        var title: String? = null
        if (addresses.isNotEmpty()) {
            val locality = listOf<String?>(
                addresses[0].countryName,
                addresses[0].adminArea,
                addresses[0].subAdminArea,
                addresses[0].locality,
                addresses[0].subAdminArea,
                addresses[0].locality,
                addresses[0].postalCode
            ).distinct().filterNotNull()
            if (locality.isNotEmpty())
                title =
                    "$locality".substring(1, "$locality".length - 1)
            else
                title = null
        }
        viewModel.setAlert(title ?: getString(R.string.Unknown))

        val markerOptions = MarkerOptions().position(latLng).title(title)


        when (addMarkerButton) {

            viewBinding.tgLatLocation,
            -> {
                viewBinding.etFirstMarker.setText("${latLng.latitude}, ${latLng.longitude}")
                val color = requireActivity().resources.getString(R.color.stroke_color);

                if (startMarker == null || isClearMap) {
                    isClearMap = false
                    startMarker = map.addMarker(markerOptions.icon(getMarkerIcon(color)))
                } else {
                    startMarker!!.title = title ?: getString(R.string.Unknown)
                    startMarker!!.position = latLng

                }

            }
            viewBinding.tgLngLocation -> {
                if (endMarker == null || isClearMap) {
                    isClearMap = false

                    endMarker =
                        map.addMarker(markerOptions.icon(BitmapDescriptorFactory.defaultMarker(
                            BitmapDescriptorFactory.HUE_RED)))
                } else {
                    endMarker!!.title = title ?: getString(R.string.Unknown)
                    endMarker!!.position = latLng
                }
                viewBinding.etSecondMarker.setText("${latLng.latitude}, ${latLng.longitude}")
            }
        }
        if (viewBinding.tgAutoZoom.isChecked)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14F))
        addMarkerButton?.isChecked = false

    }

    fun getMarkerIcon(color: String?): BitmapDescriptor? {
        val hsv = FloatArray(3)
        Color.colorToHSV(Color.parseColor(color), hsv)
        return BitmapDescriptorFactory.defaultMarker(hsv[0])
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        viewBinding.btnCoordinates.visibility = View.GONE
        return true
    }

    /**
     * Строение маршрута по координатам, также вычесление расстояния и примерного времени до точки
     */
    private fun drawRoute() {
        if (startMarker != null && endMarker != null) {
            viewModel.getDirection(
                origin = "${startMarker!!.position.latitude},${startMarker!!.position.longitude}",
                destination = "${endMarker!!.position.latitude},${endMarker!!.position.longitude}",
                mode = typeRoute
            )
            viewModel.direction.observe(viewLifecycleOwner, {
                viewBinding.tvTime.visibility = View.VISIBLE
                val steps: MutableList<MutableList<LatLng>> = mutableListOf()
                for (route in it.routes) {
                    for (leg in route.legs) {
                        viewModel.setDistance(leg.distance.value.toDouble())
                        viewBinding.tvTime.text = leg.duration.text
                        distanceText = leg.distance.text
                        for (step in leg.steps) {
                            val startLatLng = PolyUtil.decode(step.polyline.points)
                            steps.add(startLatLng)
                        }
                    }
                }
                val allPoints = mutableListOf<LatLng>()
                steps.forEach { _steps ->
                    _steps.forEach { point -> allPoints.add(point) }
                }
                if (routePolyline != null)
                    routePolyline!!.points = allPoints
                else {
                    val polylineOptions =
                        PolylineOptions().addAll(allPoints)
                            .color(requireActivity().getColor(R.color.stroke_color))
                            .startCap(RoundCap())
                            .endCap(RoundCap())
                    routePolyline = map.addPolyline(polylineOptions)
                }

            })
        } else {
            viewModel.setAlert(requireActivity().getString(R.string.invalid_coordinates))
        }

    }

    private var isClearMap: Boolean = false

    /**
     * Рисование полигона, пригодится когда надо выделить участки
     */
    private fun drawPolygon() {
        isClearMap = true
        map.clear()
        val polygons = coordinates.features[0].geometry.polygons
        Log.i("GMap", "Polygons size: ${polygons.size}")
        var distance: Double = 0.0
        val allCoordinates: MutableList<LatLng> = mutableListOf()
        val boundsBuilder = LatLngBounds.builder()

        for (_polygon in polygons) {
            val polygonOptions = PolygonOptions()
            for (_hole in _polygon.holes) {
                allCoordinates.addAll(_hole.coordinates)
                if (_polygon.coordinates == null) {
                    polygonOptions.addAll(_hole.coordinates)
                } else {
                    polygonOptions.addAll(_polygon.coordinates)
                    polygonOptions.addHole(_hole.coordinates)
                }
            }
            polygonOptions
                .strokeColor(requireActivity().getColor(R.color.stroke_color))
                .strokeWidth(5.0f)
            map.addPolygon(polygonOptions)

        }
        allCoordinates.forEach {
            boundsBuilder.include(it)
        }
        latLangBounds = boundsBuilder.build()
        addMarker(latLangBounds.center)

        if (viewBinding.tgAutoZoom.isChecked)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLangBounds.center, 1F))
        distance = SphericalUtil.computeLength(allCoordinates)
        systemGeometry = requireActivity().getString(R.string.meter_squared)
        distanceText = ""
        viewModel.setDistance(distance)
        Log.i("GMap", "Distance: $distance")
    }


}