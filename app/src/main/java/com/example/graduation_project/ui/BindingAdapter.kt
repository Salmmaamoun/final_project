package com.example.graduation_project.ui

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
@BindingAdapter("imageUri")
fun loadImage(imageView: ImageView, imageUri: Uri?) {
    imageUri?.let {
        Glide.with(imageView)
            .load(it)
            .into(imageView)
    }
}