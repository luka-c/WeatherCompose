package com.example.weatherapp.model

data class Forecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<DayForecast>,
    val message: Double
)