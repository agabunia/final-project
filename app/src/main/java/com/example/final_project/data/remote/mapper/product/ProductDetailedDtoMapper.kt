package com.example.final_project.data.remote.mapper.product

import com.example.final_project.data.remote.model.product.ProductDetailedDto
import com.example.final_project.domain.model.product.GetProductDetailed

fun ProductDetailedDto.toDomain(): GetProductDetailed {
    return GetProductDetailed(
        id = id,
        title = title,
        description = description,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        brand = brand,
        category = category,
        thumbnail = thumbnail,
        images = images
    )
}