package com.example.final_project.data.remote.service.home

import retrofit2.Response
import retrofit2.http.GET

interface CategoryListService {
    @GET("https://run.mocky.io/v3/16d925f5-4c04-4775-9f73-1faf6b6f6fcb")
    suspend fun getCategoryList(): Response<List<String>>
}