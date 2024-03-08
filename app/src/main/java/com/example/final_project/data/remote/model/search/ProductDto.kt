package com.example.final_project.data.remote.model.search

import com.example.final_project.domain.model.search.GetCategory
import com.squareup.moshi.Json

data class ProductDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "price")
    val price: Int,
    @Json(name = "description")
    val description: String,
    @Json(name = "category")
    val category: CategoryDto,
    @Json(name = "images")
    val images: List<String>
)
