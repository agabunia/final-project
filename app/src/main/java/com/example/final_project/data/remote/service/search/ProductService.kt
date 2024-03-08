package com.example.final_project.data.remote.service.search

import com.example.final_project.data.remote.model.search.ProductDto
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("https://api.escuelajs.co/api/v1/products")
    suspend fun getProduct(): Response<List<ProductDto>>
}