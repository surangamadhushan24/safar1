package com.nibm.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nibm.myapplication.R
import com.nibm.myapplication.adapters.TravelDestinationAdapter
import com.nibm.myapplication.database.AppDatabase
import com.nibm.myapplication.model.TravelDestination
import com.nibm.myapplication.repository.TravelDestinationRepository
import com.nibm.myapplication.viewModel.TravelDestinationViewModel
import com.nibm.myapplication.viewModel.TravelDestinationViewModelFactory


class DestinationFragment : Fragment() {

    private lateinit var travelDestinationViewModel: TravelDestinationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_destination, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = TravelDestinationAdapter(emptyList())
        recyclerView.adapter = adapter

        val dao = AppDatabase.getDatabase(requireContext()).travelDestinationDao()
        val repository = TravelDestinationRepository(dao)
        travelDestinationViewModel = ViewModelProvider(this, TravelDestinationViewModelFactory(repository)).get(
            TravelDestinationViewModel::class.java)

        travelDestinationViewModel.allDestinations.observe(viewLifecycleOwner) { destinations ->
            destinations?.let { adapter.updateDestinations(it) }
        }


        //insertHardcodedData()

        return view
    }

    private fun insertHardcodedData() {
        val destinations = listOf(
            TravelDestination(
                name = "Paris",
                description = "Discover the enchanting capital of France, where romance meets history. Stroll along the Seine River, marvel at the iconic Eiffel Tower, and explore world-class art in the Louvre. Savor buttery croissants at charming cafés, shop in chic boutiques, and wander through cobblestone streets lined with elegant architecture. Perfect for lovers, dreamers, and culture enthusiasts, Paris promises an unforgettable escape.",
                imageResIds = listOf(R.drawable.paris, R.drawable.paris2, R.drawable.paris3)
            ),
            TravelDestination(
                name = "Tokyo",
                description = "Dive into the electric heart of Japan, where ancient traditions blend with futuristic innovation. Visit serene shrines like Senso-ji, shop in neon-lit districts like Shibuya, and taste sushi fresh from Tsukiji Market. With towering skyscrapers, quirky themed cafés, and peaceful gardens, Tokyo offers a thrilling mix of fast-paced excitement and quiet beauty for every traveler.",
                imageResIds = listOf(R.drawable.tokyo)
            ),
            TravelDestination(
                name = "New York",
                description = "Experience the city that never sleeps, a melting pot of cultures and ambition. Gaze at the Statue of Liberty, catch a Broadway show, or climb the Empire State Building for breathtaking views. From the trendy streets of Brooklyn to the dazzling lights of Times Square, New York delivers endless energy, iconic sights, and diverse flavors for adventurers and urban explorers.",
                imageResIds = listOf(R.drawable.new_york)
            ),
            TravelDestination(
                name = "Bali",
                description = "Escape to this tropical paradise in Indonesia, where natural beauty meets spiritual serenity. Lounge on golden beaches like Kuta, hike through lush rice paddies in Ubud, or surf world-class waves. Visit ornate temples, indulge in Balinese cuisine, and unwind with yoga retreats. Bali is a haven for relaxation, adventure, and cultural immersion.",
                imageResIds = listOf(R.drawable.bali)
            ),
            TravelDestination(
                name = "Rome",
                description = "Step into the Eternal City, where every corner tells a story of ancient glory. Explore the majestic Colosseum, toss a coin into the Trevi Fountain, and savor authentic pasta in a cozy trattoria. With stunning ruins, grand piazzas, and the Vatican’s artistic treasures, Rome offers a timeless journey through history, faith, and Italian charm.",
                imageResIds = listOf(R.drawable.rome)
            )
        )

        destinations.forEach { travelDestinationViewModel.insert(it) }
    }
}