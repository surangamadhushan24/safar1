package com.nibm.myapplication

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nibm.myapplication.databinding.ActivityFullscreenImageBinding

class FullScreenImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullscreenImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityFullscreenImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve Image URI
        val imageUri = intent.getStringExtra("image_uri")

        // Load the image if the URI is valid
        imageUri?.let {
            binding.imageViewFull.setImageURI(Uri.parse(it))
        }
    }
}
