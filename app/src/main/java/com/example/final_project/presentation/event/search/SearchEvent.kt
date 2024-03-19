package com.example.final_project.presentation.event.search

import com.example.final_project.presentation.model.common_product_list.Products

sealed class SearchEvent {
    object FetchAllProducts: SearchEvent()
    data class FetchSearchProducts(val search: String): SearchEvent()
    object MoveToDetailed: SearchEvent()
    object ResetErrorMessage: SearchEvent()
    data class SaveProduct(var product: Products.ProductDetailed): SearchEvent()
}