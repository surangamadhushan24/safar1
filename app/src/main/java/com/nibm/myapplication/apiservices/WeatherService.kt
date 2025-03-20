package com.nibm.myapplication.apiservices

import com.nibm.myapplication.model.ForecastResponse
import com.nibm.myapplication.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String
    ): WeatherResponse

    @GET("data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("q") cityName: String,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String
    ): ForecastResponse
}