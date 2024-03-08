package com.example.final_project.data.remote.service.search

import com.example.final_project.data.remote.model.search.CategoryDto
import retrofit2.Response
import retrofit2.http.GET

interface CategoryService {
    @GET("https://api.escuelajs.co/api/v1/categories")
    suspend fun getCategory(): Response<List<CategoryDto>>
}