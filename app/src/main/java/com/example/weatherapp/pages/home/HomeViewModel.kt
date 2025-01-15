package com.example.weatherapp.pages.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.di.Session
import com.example.weatherapp.model.Forecast
import com.example.weatherapp.network.WeatherAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val api: WeatherAPI,
    private val session: Session
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    private var units by mutableStateOf("metric")

    private val _forecast = MutableStateFlow<Forecast?>(null)
    val forecast: StateFlow<Forecast?> = _forecast.asStateFlow()

    init {
        viewModelScope.launch {
            session.getUnits().collect {
                units = it
            }
        }
    }

    fun getForecast(city: String = "Zagreb") = viewModelScope.launch {
        isLoading = true
        val response = api.getWeatherForecast(city = city, units = units)

        if (response.isSuccessful) {
            _forecast.value = response.body()
            error = null
            delay(1000)
            isLoading = false
        }
        else {
            _forecast.value = null
            error = "Could not fetch forecast"
            isLoading = false
        }
    }

}