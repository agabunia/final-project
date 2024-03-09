package com.example.final_project.domain.model.search

data class GetProducts(
    val products: List<GetProductDetailed>
) {
    data class GetProductDetailed(
        val id: String,
        val title: String,
        val price: Int,
        val thumbnail: String,
    )
}