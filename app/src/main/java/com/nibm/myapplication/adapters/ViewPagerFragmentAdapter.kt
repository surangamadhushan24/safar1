package com.nibm.myapplication.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nibm.myapplication.fragment.DestinationFragment
import com.nibm.myapplication.fragment.GalleryFragment
import com.nibm.myapplication.fragment.MapFragment
import com.nibm.myapplication.fragment.WeatherFragment

class ViewPagerFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DestinationFragment()
            1 -> WeatherFragment()
            2 -> MapFragment()
            3 -> GalleryFragment()

            else -> DestinationFragment()
        }
    }

    override fun getItemCount(): Int = 4
}