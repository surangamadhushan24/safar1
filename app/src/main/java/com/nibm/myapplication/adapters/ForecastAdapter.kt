package com.nibm.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nibm.myapplication.R
import com.nibm.myapplication.model.Forecast

class ForecastAdapter(private val forecastList: List<Forecast>) : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecast = forecastList[position]
        holder.bind(forecast)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTime: TextView = itemView.findViewById(R.id.dateTime)
        private val temperature: TextView = itemView.findViewById(R.id.temperature)
        private val weatherIcon: ImageView = itemView.findViewById(R.id.weatherIcon)

        fun bind(forecast: Forecast) {
            dateTime.text = forecast.dt_txt
            temperature.text = "Temp: ${forecast.main.temp}°C"
            val iconUrl = "https://openweathermap.org/img/wn/${forecast.weather[0].icon}@2x.png"
            Glide.with(itemView.context).load(iconUrl).into(weatherIcon)
        }
    }
}