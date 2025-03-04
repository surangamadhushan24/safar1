package com.nibm.myapplication.model

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>
)