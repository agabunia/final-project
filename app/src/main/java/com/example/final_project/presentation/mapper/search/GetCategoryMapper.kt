package com.example.final_project.presentation.mapper.search

import com.example.final_project.domain.model.search.GetCategory
import com.example.final_project.presentation.model.search.Category

fun GetCategory.toPresenter(): Category {
    return Category(
        id = id,
        name = name,
        image = image
    )
}