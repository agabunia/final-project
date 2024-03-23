package com.example.final_project.data.remote.model.home

import com.squareup.moshi.Json

data class ImageDto(
    @Json(name = "images")
    val images: String
)
