package com.nibm.myapplication

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nibm.myapplication.databinding.ActivityFullscreenImageBinding

class FullScreenImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullscreenImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityFullscreenImageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val imageUri = intent.getStringExtra("image_uri")


        imageUri?.let {
            binding.imageViewFull.setImageURI(Uri.parse(it))
        }
    }
}
