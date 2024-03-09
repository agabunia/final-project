package com.example.final_project.data.remote.mapper.search

import com.example.final_project.data.remote.model.search.ProductsDto
import com.example.final_project.domain.model.search.GetProducts

fun ProductsDto.toDomain(): GetProducts {
    val getProducts = products.map {
        GetProducts.GetProductDetailed(
            id = it.id,
            title = it.title,
            price = it.price,
            thumbnail = it.thumbnail
        )
    }
    return GetProducts(products = getProducts)
}