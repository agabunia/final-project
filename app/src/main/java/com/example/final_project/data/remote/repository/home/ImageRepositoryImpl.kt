package com.example.final_project.data.remote.repository.home

import android.util.Log.d
import com.example.final_project.data.common.HandleResponse
import com.example.final_project.data.common.Resource
import com.example.final_project.data.remote.mapper.base.asResource
import com.example.final_project.data.remote.mapper.home.toDomain
import com.example.final_project.data.remote.service.home.ImageService
import com.example.final_project.domain.remote.model.home.GetImage
import com.example.final_project.domain.remote.repository.home.ImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val handleResponse: HandleResponse,
    private val imageService: ImageService
) : ImageRepository {
    override suspend fun getImage(): Flow<Resource<GetImage>> {
        return handleResponse.safeApiCall {
            imageService.getImage()
        }.asResource {
            d("fetchedImage", it.images)
            it.toDomain()
        }
    }
}