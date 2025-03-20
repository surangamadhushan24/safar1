package com.nibm.myapplication.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nibm.myapplication.DestinationDetailActivity
import com.nibm.myapplication.R
import com.nibm.myapplication.model.TravelDestination

class TravelDestinationAdapter(private var destinations: List<TravelDestination>) :
    RecyclerView.Adapter<TravelDestinationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val textViewName: TextView = view.findViewById(R.id.textViewName)
//        val textViewDescription: TextView = view.findViewById(R.id.textViewDescription)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_travel_destination, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val destination = destinations[position]
        viewHolder.imageView.setImageResource(destination.imageResIds[0])
        viewHolder.textViewName.text = destination.name
//        viewHolder.textViewDescription.text = destination.description

        // Set click listener for the item
        viewHolder.itemView.setOnClickListener {
            val context = viewHolder.itemView.context
            val intent = Intent(context, DestinationDetailActivity::class.java).apply {
                putExtra("name", destination.name)
                putExtra("description", destination.description)
                putIntegerArrayListExtra("imageResIds", ArrayList(destination.imageResIds))
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = destinations.size

    fun updateDestinations(newDestinations: List<TravelDestination>) {
        destinations = newDestinations
        notifyDataSetChanged()
    }
}