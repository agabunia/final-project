package com.example.final_project.data.remote.repository.maps

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.final_project.domain.repository.maps.PlaceLocationRepository
import com.google.android.gms.tasks.Tasks
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlaceLocationRepositoryImpl @Inject constructor(
    private val context: Context,
    private val placesClient: PlacesClient
) : PlaceLocationRepository {

    override suspend fun getPlacesLocation(placeName: String): Location? {
        Log.d("PlaceLocationRepository", "Searching for location of place: $placeName")
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null
        }

        val placeFields = listOf(Place.Field.LAT_LNG)
        val request = FindCurrentPlaceRequest.newInstance(placeFields)


        val placeResponse = withContext(Dispatchers.IO) {
            Tasks.await(placesClient.findCurrentPlace(request))
        }

        val placeLikelihoodList = placeResponse.placeLikelihoods

        for (placeLikelihood in placeLikelihoodList) {
            Log.d("PlaceLocationRepository", "Number of places found: ${placeLikelihoodList.size}")
            val place = placeLikelihood.place
            if (place.name == placeName) {
                Log.d("PlaceLocationRepository", "Checking place: ${place.name}")
                return Location("").apply {
                    latitude = place.latLng?.latitude ?: 0.0
                    longitude = place.latLng?.longitude ?: 0.0
                }
            }
        }

        return null
    }
}