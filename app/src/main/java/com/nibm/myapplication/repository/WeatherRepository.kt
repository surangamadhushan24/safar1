package com.nibm.myapplication.repository

import com.nibm.myapplication.BuildConfig
import com.nibm.myapplication.apiservices.WeatherService
import com.nibm.myapplication.model.ForecastResponse
import com.nibm.myapplication.model.WeatherResponse

class WeatherRepository(private val weatherService: WeatherService) {

    // Fetch current weather
    suspend fun getCurrentWeather(cityName: String): WeatherResponse {
        return weatherService.getCurrentWeather(
            cityName,
            "metric",
            BuildConfig.OPENWEATHERMAP_API_KEY
        )
    }

    // Fetch weather forecast
    suspend fun getWeatherForecast(cityName: String): ForecastResponse {
        return weatherService.getWeatherForecast(
            cityName,
            "metric",
            BuildConfig.OPENWEATHERMAP_API_KEY
        )
    }
}