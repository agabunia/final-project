package com.example.final_project.presentation.mapper.search

import com.example.final_project.domain.model.search.GetProducts
import com.example.final_project.presentation.model.search.Products

fun GetProducts.toPresenter(): Products {
    val products = products.map {
        Products.ProductDetailed(
            id = it.id,
            title = it.title,
            price = it.price,
            thumbnail = it.thumbnail
        )
    }
    return Products(products = products)
}