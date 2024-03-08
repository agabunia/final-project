package com.example.final_project.presentation.extention

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadImage(url: String?) {
    Glide.with(context)
        .load(url)
//        .placeholder(R.drawable.ic_launcher_background)
        .into(this);
}