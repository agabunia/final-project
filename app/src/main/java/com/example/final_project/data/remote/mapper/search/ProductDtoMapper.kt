package com.example.final_project.data.remote.mapper.search

import com.example.final_project.data.remote.model.search.ProductDto
import com.example.final_project.domain.model.search.GetProduct

fun ProductDto.toDomain(): GetProduct {
    return GetProduct(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category.toDomain(),
        images = images
    )
}