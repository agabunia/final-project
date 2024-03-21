package com.example.final_project.domain.usecase.maps

import com.example.final_project.domain.repository.maps.UserLocationRepository
import javax.inject.Inject

class UserLocationPlaceUseCase @Inject constructor(
    private val userLocationRepository: UserLocationRepository
) {
    suspend operator fun invoke() = userLocationRepository.getUserLocation()
}