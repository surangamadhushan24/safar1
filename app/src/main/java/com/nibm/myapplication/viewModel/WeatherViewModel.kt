package com.nibm.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nibm.myapplication.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    fun fetchWeather(city: String, apiKey: String) = liveData(Dispatchers.IO) {
        val response = repository.getWeather(city, apiKey)
        emit(response) // Updates LiveData with weather data
    }
}
