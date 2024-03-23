package com.example.final_project.data.remote.service.home

import com.example.final_project.data.remote.model.home.ImageDto
import retrofit2.Response
import retrofit2.http.GET

interface ImageService {
    @GET("https://run.mocky.io/v3/2160d9dc-a457-4020-af85-3761a22ef469")
    suspend fun getImage(): Response<ImageDto>
}