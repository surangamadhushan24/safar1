package com.nibm.myapplication.apiservices

import com.nibm.myapplication.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

interface WeatherApiService {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
//        @Query("units") units: String = "metric"
    ): WeatherResponse

    companion object {
        fun create(): WeatherApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(WeatherApiService::class.java)
        }
    }
}
