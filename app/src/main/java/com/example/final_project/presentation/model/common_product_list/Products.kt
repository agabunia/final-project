package com.example.final_project.presentation.model.common_product_list

data class Products(
    val products: List<ProductDetailed>
) {
    data class ProductDetailed(
        val id: Int,
        val title: String,
        val price: Int,
        val thumbnail: String,
        var isLiked: Boolean = false,
        var isAdded: Boolean = false
    )
}