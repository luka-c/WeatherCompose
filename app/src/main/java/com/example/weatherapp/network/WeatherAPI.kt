package com.example.weatherapp.network

import com.example.weatherapp.model.Forecast
import com.example.weatherapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherAPI {

    @GET("data/2.5/forecast/daily")
    suspend fun getWeatherForecast(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("cnt") noOfDays: Int = 7,
        @Query("appid") apiKey: String = Constants.API_KEY
    ): Response<Forecast>
}