package com.example.final_project.data.remote.repository.search

import com.example.final_project.data.common.HandleResponse
import com.example.final_project.data.common.Resource
import com.example.final_project.data.remote.mapper.base.asResource
import com.example.final_project.data.remote.mapper.search.toDomain
import com.example.final_project.data.remote.service.search.ProductService
import com.example.final_project.domain.model.search.GetProduct
import com.example.final_project.domain.repository.search.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val handleResponse: HandleResponse,
    private val productService: ProductService
) : ProductRepository {
    override suspend fun getProducts(): Flow<Resource<List<GetProduct>>> {
        return handleResponse.safeApiCall {
            productService.getProduct()
        }.asResource { list ->
            list.map {
                it.toDomain()
            }
        }
    }
}