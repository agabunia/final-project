package com.example.final_project.presentation.state.search

import com.example.final_project.presentation.model.search.Category
import com.example.final_project.presentation.model.search.Product

data class SearchState(
    val category: List<Category>? = null,
    val productsList: List<Product>? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
