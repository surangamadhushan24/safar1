package com.nibm.myapplication



import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class FullScreenImageActivity : AppCompatActivity() {

    private val imageViewFull: ImageView = findViewById(R.id.imageView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen_image)

        val imageUri = intent.getStringExtra("image_uri")
        imageViewFull.setImageURI(Uri.parse(imageUri))
    }
}
