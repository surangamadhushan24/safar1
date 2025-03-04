package com.nibm.myapplication.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nibm.myapplication.FullScreenImageActivity
import com.nibm.myapplication.R
import com.nibm.myapplication.adapters.ImageAdapter
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class GalleryFragment : Fragment() {

    private val REQUEST_CAMERA = 1
    private val REQUEST_GALLERY = 2
    private var photoUri: Uri? = null
    private val imageList = mutableListOf<Uri>()
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val btnCamera = view.findViewById<Button>(R.id.btn_camera)
        val btnGallery = view.findViewById<Button>(R.id.btn_gallery)

        imageAdapter = ImageAdapter(imageList) { uri ->
            val intent = Intent(requireContext(), FullScreenImageActivity::class.java)
            intent.putExtra("image_uri", uri.toString())
            startActivity(intent)
        }

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = imageAdapter

        btnCamera.setOnClickListener { openCamera() }
        btnGallery.setOnClickListener { openGallery() }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            val photoFile: File? = try {
                createImageFile()
            } catch (ex: IOException) {
                Toast.makeText(requireContext(), "Error creating file!", Toast.LENGTH_SHORT).show()
                null
            }
            photoFile?.also {
                photoUri = FileProvider.getUriForFile(requireContext(), "com.nibm.myapplication.fileprovider", it)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(intent, REQUEST_CAMERA)
            }
        }
    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = requireContext().getExternalFilesDir(null)
        return File.createTempFile("IMG_${timeStamp}_", ".jpg", storageDir)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CAMERA -> {
                    photoUri?.let {
                        imageList.add(it)
                        imageAdapter.notifyDataSetChanged()
                    }
                }
                REQUEST_GALLERY -> {
                    data?.data?.let {
                        imageList.add(it)
                        imageAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}