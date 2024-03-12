package com.example.final_project.domain.repository.home

import com.example.final_project.data.common.Resource
import com.example.final_project.domain.model.common_product_list.GetProducts
import kotlinx.coroutines.flow.Flow

interface ProductByCategoryRepository {
    suspend fun getProductByCategory(category: String): Flow<Resource<GetProducts>>
}