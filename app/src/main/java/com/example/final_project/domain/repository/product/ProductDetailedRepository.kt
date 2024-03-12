package com.example.final_project.domain.repository.product

import com.example.final_project.data.common.Resource
import com.example.final_project.domain.model.product.GetProductDetailed
import kotlinx.coroutines.flow.Flow

interface ProductDetailedRepository {
    suspend fun getProductDetailed(id: Int): Flow<Resource<GetProductDetailed>>
}