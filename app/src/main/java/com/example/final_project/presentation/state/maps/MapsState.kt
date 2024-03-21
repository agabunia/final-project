package com.example.final_project.presentation.state.maps

import android.location.Location

data class MapsState(
    val errorMessage: String? = null,
    val userLocation: Location? = null,
    val placeLocation: Location? = null
)
