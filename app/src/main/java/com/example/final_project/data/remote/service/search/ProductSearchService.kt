package com.example.final_project.data.remote.service.search

import com.example.final_project.data.remote.model.search.ProductsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//interface ProductSearchService {
//    @GET("https://dummyjson.com/products/search?q={search}")
//    suspend fun getProduct(
//        @Path("search") search: String
//    ): Response<ProductsDto>
//}

interface ProductSearchService {
    @GET("https://dummyjson.com/products/search")
    suspend fun getProduct(
        @Query("q") search: String
    ): Response<ProductsDto>
}