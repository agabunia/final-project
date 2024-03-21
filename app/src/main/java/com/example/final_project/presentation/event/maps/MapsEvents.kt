package com.example.final_project.presentation.event.maps

sealed class MapsEvents {
    data object GetUserLocation : MapsEvents()
    data class GetPlaceLocation(val placeName: String) : MapsEvents()
}