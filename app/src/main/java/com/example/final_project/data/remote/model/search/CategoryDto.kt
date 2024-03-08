package com.example.final_project.data.remote.model.search

import com.squareup.moshi.Json

data class CategoryDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "image")
    val image: String
)
