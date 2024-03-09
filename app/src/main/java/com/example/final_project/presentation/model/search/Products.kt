package com.example.final_project.presentation.model.search

data class Products(
    val products: List<ProductDetailed>
) {
    data class ProductDetailed(
        val id: String,
        val title: String,
        val price: Int,
        val thumbnail: String,
    )
}