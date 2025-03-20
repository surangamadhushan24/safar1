package com.nibm.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nibm.myapplication.R
import com.nibm.myapplication.adapter.ForecastAdapter
import com.nibm.myapplication.apiservices.WeatherService
import com.nibm.myapplication.repository.WeatherRepository
import com.nibm.myapplication.viewModel.WeatherViewModel
import com.nibm.myapplication.viewModel.WeatherViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val weatherService = retrofit.create(WeatherService::class.java)


        val repository = WeatherRepository(weatherService)


        viewModel = ViewModelProvider(this, WeatherViewModelFactory(repository)).get(
            WeatherViewModel::class.java)


        val searchView = view.findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchCity(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


        viewModel.weatherData.observe(viewLifecycleOwner) { weatherResponse ->
            view.findViewById<TextView>(R.id.cityName).text = weatherResponse.name
            view.findViewById<TextView>(R.id.temperature).text = "Temperature: ${weatherResponse.main.temp}°C"
            view.findViewById<TextView>(R.id.weatherDescription).text = "Weather: ${weatherResponse.weather[0].description}"
            view.findViewById<TextView>(R.id.humidity).text = "Humidity: ${weatherResponse.main.humidity}%"


            val iconUrl = "https://openweathermap.org/img/wn/${weatherResponse.weather[0].icon}@2x.png"
            Glide.with(this).load(iconUrl).into(view.findViewById(R.id.weatherIcon))
        }

        // Observe forecast data
        val forecastRecyclerView = view.findViewById<RecyclerView>(R.id.forecastRecyclerView)
        forecastRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.forecastData.observe(viewLifecycleOwner) { forecastResponse ->
            val forecastList = forecastResponse.list
            val adapter = ForecastAdapter(forecastList)
            forecastRecyclerView.adapter = adapter
        }


        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            view.findViewById<TextView>(R.id.errorMessage).text = errorMessage
        }

        viewModel.searchCity("Matara")

        return view
    }
}