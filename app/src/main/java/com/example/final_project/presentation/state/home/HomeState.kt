package com.example.final_project.presentation.state.home

import com.example.final_project.presentation.model.home.CategoryWrapperList

data class HomeState(
    val productsList: List<CategoryWrapperList>? = null,
    val image: String? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
