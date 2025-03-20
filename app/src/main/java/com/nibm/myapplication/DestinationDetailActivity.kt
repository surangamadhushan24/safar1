package com.nibm.myapplication


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.nibm.myapplication.adapters.ImagePagerAdapter
import com.nibm.myapplication.databinding.ActivityDestinationDetailBinding


class DestinationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinationDetailBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var imageAdapter: ImagePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDestinationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val imageResIds = intent.getIntegerArrayListExtra("imageResIds") ?: ArrayList()


        binding.textViewName.text = name
        binding.textViewDescription.text = description


        viewPager = binding.viewPager
        imageAdapter = ImagePagerAdapter(imageResIds)
        viewPager.adapter = imageAdapter
    }
}