package com.nibm.myapplication.model

data class Forecast(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val dt_txt: String
)
