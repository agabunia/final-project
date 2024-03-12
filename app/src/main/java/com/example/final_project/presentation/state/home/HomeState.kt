package com.example.final_project.presentation.state.home

import com.example.final_project.presentation.model.home.CategoryList

data class HomeState(
    val productsList: List<CategoryList>? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
