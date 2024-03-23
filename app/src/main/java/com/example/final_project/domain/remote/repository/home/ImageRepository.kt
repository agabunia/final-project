package com.example.final_project.domain.remote.repository.home

import com.example.final_project.data.common.Resource
import com.example.final_project.domain.remote.model.home.GetImage
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun getImage(): Flow<Resource<GetImage>>
}