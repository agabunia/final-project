package com.example.final_project.domain.repository.search

import com.example.final_project.data.common.Resource
import com.example.final_project.domain.model.search.GetCategory
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategory(): Flow<Resource<List<GetCategory>>>
}