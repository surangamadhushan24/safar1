package com.nibm.myapplication.fragment



import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nibm.myapplication.R
import java.io.IOException

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        searchView = view.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchCity(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return view
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
    }

    private fun searchCity(cityName: String) {
        val geocoder = Geocoder(requireContext())
        try {

            val addresses: List<Address>? = geocoder.getFromLocationName(cityName, 1)
            if (!addresses.isNullOrEmpty()) {
                val address = addresses[0]
                val location = LatLng(address.latitude, address.longitude)
                googleMap.clear()
                googleMap.addMarker(MarkerOptions().position(location).title(cityName))
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))
            } else {
                showMessage("City not found")
            }
        } catch (e: IOException) {
            e.printStackTrace()
            showMessage("Error searching for city")
        }
    }


    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
