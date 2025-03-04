package com.nibm.myapplication.repository

import com.nibm.myapplication.apiservices.WeatherApiService
import com.nibm.myapplication.model.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository(private val apiService: WeatherApiService) {

    suspend fun getWeather(city: String, apiKey: String): WeatherResponse? {
        return withContext(Dispatchers.IO) { // Background thread for API call
            try {
                apiService.getWeather(city, apiKey)
            } catch (e: Exception) {
                null // Handle errors gracefully
            }
        }
    }
}
