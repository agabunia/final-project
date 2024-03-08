package com.example.final_project.domain.model.search

data class GetProduct(
    val id: Int,
    val title: String,
    val price: Int,
    val description: String,
    val category: GetCategory,
    val images: List<String>
)