package com.example.final_project.domain.repository.search

import com.example.final_project.data.common.Resource
import com.example.final_project.domain.model.search.GetProducts
import kotlinx.coroutines.flow.Flow

interface ProductSearchRepository {
    suspend fun searchProduct(search: String): Flow<Resource<GetProducts>>
}