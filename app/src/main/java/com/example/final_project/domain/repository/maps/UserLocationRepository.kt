package com.example.final_project.domain.repository.maps

import android.location.Location

interface UserLocationRepository {
    suspend fun getUserLocation(): Location?
}