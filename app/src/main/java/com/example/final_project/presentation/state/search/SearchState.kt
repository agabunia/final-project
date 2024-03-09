package com.example.final_project.presentation.state.search

import com.example.final_project.presentation.model.search.Products

data class SearchState(
    val productsList: List<Products.ProductDetailed>? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
