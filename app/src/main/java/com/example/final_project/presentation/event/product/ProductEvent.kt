package com.example.final_project.presentation.event.product

import com.example.final_project.presentation.model.product.ProductDetailed

sealed class ProductEvent {
    data class FetchProductDetailed(val id: Int): ProductEvent()
    object ResetErrorMessage: ProductEvent()
    data class SaveProduct(var product: ProductDetailed): ProductEvent()
}