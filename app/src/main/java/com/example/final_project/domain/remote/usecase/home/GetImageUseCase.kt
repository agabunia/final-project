package com.example.final_project.domain.remote.usecase.home

import com.example.final_project.data.remote.service.home.ImageService
import com.example.final_project.domain.remote.repository.home.ImageRepository
import javax.inject.Inject

class GetImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {
    suspend operator fun invoke() = imageRepository.getImage()
}