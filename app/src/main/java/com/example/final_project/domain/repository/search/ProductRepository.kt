package com.example.final_project.domain.repository.search

import com.example.final_project.data.common.Resource
import com.example.final_project.domain.model.search.GetProducts
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(): Flow<Resource<GetProducts>>
}