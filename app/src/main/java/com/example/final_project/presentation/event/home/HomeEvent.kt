package com.example.final_project.presentation.event.home

import com.example.final_project.presentation.model.common_product_list.Products

sealed class HomeEvent {
    data class FetchProducts(val category: List<String>) : HomeEvent()
    object FetchCategoryList : HomeEvent()
    object ResetErrorMessage : HomeEvent()
    data class ChangeTheme(val isLight: Boolean) : HomeEvent()
    data class ChangeLanguage(val isGeorgian: Boolean) : HomeEvent()
    data class SaveProduct(var product: Products.ProductDetailed): HomeEvent()
    data class MoveToDetailed(val id: Int): HomeEvent()
}