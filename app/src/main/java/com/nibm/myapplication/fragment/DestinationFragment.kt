package com.nibm.myapplication.fragment



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nibm.myapplication.R
import com.nibm.myapplication.adapters.TravelDestinationAdapter
import com.nibm.myapplication.model.TravelDestination

class DestinationFragment : Fragment() {


    private val destinations = listOf(
        TravelDestination(
            "Paris",
            "The city of love and lights.",
            listOf(R.drawable.paris, R.drawable.paris2, R.drawable.paris3)
        ),
        TravelDestination(
            "Tokyo",
            "A bustling metropolis with a mix of tradition and modernity.",
            listOf(R.drawable.tokyo)
        ),
        TravelDestination(
            "New York",
            "The city that never sleeps.",
            listOf(R.drawable.new_york)
        ),
        TravelDestination(
            "Bali",
            "A tropical paradise with beautiful beaches.",
            listOf(R.drawable.bali)
        ),
        TravelDestination(
            "Rome",
            "The eternal city with ancient history.",
            listOf(R.drawable.rome)
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_destination, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = TravelDestinationAdapter(destinations)

        return view
    }
}
