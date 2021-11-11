package com.maandraj.geomap.features.map.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maandraj.geomap.features.map.data.domain.CoordinatesInteractor
import com.maandraj.geomap.features.map.data.domain.model.Coordinates
import com.maandraj.geomap.utils.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val coordinatesInteractor: CoordinatesInteractor,
) : ViewModel() {

    private val _coordinates = MutableLiveData<Coordinates>()
    val coordinates = _coordinates.asLiveData()

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState = _loadingState.asLiveData()

    private val _distance = MutableLiveData<Double>()
    val distance = _distance.asLiveData()

    init {
        getAllCoordinates()
    }
    fun getAllCoordinates(){
        viewModelScope.launch {
            _loadingState.postValue(true)
            _coordinates.postValue(coordinatesInteractor.getAllCoordinates())
            _loadingState.postValue(false)
        }
    }
    fun setDistance(distance:Double){
        viewModelScope.launch {

            _distance.postValue(distance)
        }
    }
}