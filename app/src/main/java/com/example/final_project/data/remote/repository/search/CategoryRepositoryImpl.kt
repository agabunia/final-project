package com.example.final_project.data.remote.repository.search

import com.example.final_project.data.common.HandleResponse
import com.example.final_project.data.common.Resource
import com.example.final_project.data.remote.mapper.base.asResource
import com.example.final_project.data.remote.mapper.search.toDomain
import com.example.final_project.data.remote.service.search.CategoryService
import com.example.final_project.domain.model.search.GetCategory
import com.example.final_project.domain.repository.search.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val handleResponse: HandleResponse,
    private val categoryService: CategoryService
) : CategoryRepository {
    override suspend fun getCategory(): Flow<Resource<List<GetCategory>>> {
        return handleResponse.safeApiCall {
            categoryService.getCategory()
        }.asResource { list ->
            list.map {
                it.toDomain()
            }
        }
    }
}
