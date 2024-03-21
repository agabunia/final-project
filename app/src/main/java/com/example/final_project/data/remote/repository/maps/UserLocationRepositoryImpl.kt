package com.example.final_project.data.remote.repository.maps

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.example.final_project.domain.repository.maps.UserLocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserLocationRepositoryImpl @Inject constructor(
    private val context: Context,
    private val locationProvider: FusedLocationProviderClient
) : UserLocationRepository {

    override suspend fun getUserLocation(): Location? {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return null
        }
        return try {
            locationProvider.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null).await()
        } catch (e: Exception) {
            null
        }
    }
}