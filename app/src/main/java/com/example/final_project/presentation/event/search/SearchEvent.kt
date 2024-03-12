package com.example.final_project.presentation.event.search

sealed class SearchEvent {
    object FetchAllProducts: SearchEvent()
    data class FetchSearchProducts(val search: String): SearchEvent()
    object MoveToDetailed: SearchEvent()
    object ResetErrorMessage: SearchEvent()
}