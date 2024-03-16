package com.example.final_project.presentation.event.home

sealed class HomeEvent {
    data class FetchProducts(val category: List<String>) : HomeEvent()
    object ResetErrorMessage : HomeEvent()
    data class ChangeTheme(val isLight: Boolean) : HomeEvent()
    data class ChangeLanguage(val isGeorgian: Boolean) : HomeEvent()
}