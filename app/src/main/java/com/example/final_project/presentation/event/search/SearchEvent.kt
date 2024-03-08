package com.example.final_project.presentation.event.search

sealed class SearchEvent {
    object FetchCategory: SearchEvent()
    object FetchAllProducts: SearchEvent()
    object ResetErrorMessage: SearchEvent()
}