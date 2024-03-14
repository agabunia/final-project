package com.example.final_project.presentation.mapper.product

import com.example.final_project.domain.model.product.GetProductDetailed
import com.example.final_project.presentation.model.product.ProductDetailed

fun GetProductDetailed.toPresenter(): ProductDetailed {
    return ProductDetailed(
        id = id,
        title = title,
        description = description,
        price = price,
        discountPercentage = discountPercentage,
        rating = Math.round(rating),
        stock = stock,
        brand = brand,
        category = category,
        thumbnail = thumbnail,
        images = images
    )
}