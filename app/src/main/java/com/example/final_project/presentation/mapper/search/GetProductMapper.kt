package com.example.final_project.presentation.mapper.search

import com.example.final_project.domain.model.search.GetProduct
import com.example.final_project.presentation.model.search.Product

fun GetProduct.toPresenter(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category.toPresenter(),
        images = images
    )
}