package com.example.final_project.data.remote.model.search

import com.squareup.moshi.Json

data class ProductsDto(
    @Json(name = "products")
    val products: List<ProductDetailedDto>
) {
    data class ProductDetailedDto(
        @Json(name = "id")
        val id: String,
        @Json(name = "title")
        val title: String,
        @Json(name = "price")
        val price: Int,
        @Json(name = "thumbnail")
        val thumbnail: String,
    )
}
