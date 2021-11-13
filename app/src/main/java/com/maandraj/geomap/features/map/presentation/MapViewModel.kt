package com.maandraj.geomap.features.map.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maandraj.geomap.features.map.data.domain.CoordinatesInteractor
import com.maandraj.geomap.features.map.data.domain.model.Coordinates
import com.maandraj.geomap.features.map.data.domain.model.direction.Direction
import com.maandraj.geomap.utils.SingleLiveEvent
import com.maandraj.geomap.utils.asLiveData
import com.squareup.moshi.JsonDataException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val coordinatesInteractor: CoordinatesInteractor,
) : ViewModel() {

    private val _coordinates = MutableLiveData<Coordinates>()
    val coordinates = _coordinates.asLiveData()

    private val _direction = MutableLiveData<Direction>()
    val direction = _direction.asLiveData()

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState = _loadingState.asLiveData()

    private val _alert = SingleLiveEvent<String>()
    val alert = _alert.asLiveData()

    private val _distance = MutableLiveData<Double>()
    val distance = _distance.asLiveData()



    /**
     * Получение координат из сервера
     */
    fun getServerCoordinates() {
        viewModelScope.launch {
            _loadingState.postValue(true)

            try {
                _coordinates.postValue(coordinatesInteractor.getAllCoordinates())

            } catch (ex: IOException) {
                _alert.postValue("Проверьте подключение к интернету.")


            } catch (ex: Exception) {
                _alert.postValue("Произошла ошибка, попробуйте снова.")
            }
            _loadingState.postValue(false)



        }
}

        /**
         * Получение маршрута
         */
        fun getDirection(
            origin: String,
            destination: String,
            language: String = Locale.getDefault().language,
            mode: String = "walking",
        ) {
            viewModelScope.launch {
                _loadingState.postValue(true)

                try {
                    val direction = coordinatesInteractor.getDirection(origin = origin,
                        destination = destination,
                        language = language,
                        mode = mode)
                    _direction.postValue(direction)

                }
                catch (ex: IOException) {
                    _alert.postValue("Проверьте подключение к интернету.")

                } catch (ex: JsonDataException) {
                    _alert.postValue("Маршрутов не найдено.")
                } catch (ex: Exception) {
                    _alert.postValue("Произошла ошибка, попробуйте снова.")
                }
                _loadingState.postValue(false)


            }}

            fun setDistance(distance: Double) {
                viewModelScope.launch {

                    _distance.postValue(distance)
                }
            }

            /**
             * Отправка сообщения пользователю
             */
            fun setAlert(text: String) {
                viewModelScope.launch {
                    _alert.postValue(text)
                }
            }

}