package com.example.final_project.domain.repository.maps

import android.location.Location

interface PlaceLocationRepository {
    suspend fun getPlacesLocation(placeName: String): Location?
}