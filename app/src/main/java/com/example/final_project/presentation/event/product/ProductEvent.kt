package com.example.final_project.presentation.event.product

sealed class ProductEvent {
    data class FetchProductDetailed(val id: Int): ProductEvent()
    object ResetErrorMessage: ProductEvent()
}