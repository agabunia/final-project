package com.example.final_project.domain.usecase.maps

import com.example.final_project.domain.repository.maps.PlaceLocationRepository
import javax.inject.Inject

class GetPlaceLocationUseCase @Inject constructor(
    private val placeLocationRepository: PlaceLocationRepository
) {
    suspend operator fun invoke(placeName: String) = placeLocationRepository.getPlacesLocation(placeName = placeName)
}