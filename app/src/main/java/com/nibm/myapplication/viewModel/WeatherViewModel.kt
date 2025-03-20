package com.nibm.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nibm.myapplication.model.ForecastResponse
import com.nibm.myapplication.model.WeatherResponse
import com.nibm.myapplication.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    // LiveData for current weather
    private val _weatherData = MutableLiveData<WeatherResponse>()
    val weatherData: LiveData<WeatherResponse> get() = _weatherData

    // LiveData for forecast
    private val _forecastData = MutableLiveData<ForecastResponse>()
    val forecastData: LiveData<ForecastResponse> get() = _forecastData

    // LiveData for error messages
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    // Search for a city and fetch both current weather and forecast
    fun searchCity(cityName: String) {
        viewModelScope.launch {
            try {
                // Fetch current weather
                val currentWeather = repository.getCurrentWeather(cityName)
                _weatherData.value = currentWeather

                // Fetch forecast
                val forecast = repository.getWeatherForecast(cityName)
                _forecastData.value = forecast
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching weather data: ${e.message}"
            }
        }
    }
}