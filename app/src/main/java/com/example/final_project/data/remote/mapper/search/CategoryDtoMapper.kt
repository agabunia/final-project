package com.example.final_project.data.remote.mapper.search

import com.example.final_project.data.remote.model.search.CategoryDto
import com.example.final_project.domain.model.search.GetCategory

fun CategoryDto.toDomain(): GetCategory {
    return GetCategory(
        id = id,
        name = name,
        image = image
    )
}