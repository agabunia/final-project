package com.example.final_project.data.remote.mapper.home

import com.example.final_project.data.remote.model.home.ImageDto
import com.example.final_project.domain.remote.model.home.GetImage

fun ImageDto.toDomain(): GetImage {
    return GetImage(
        images = images
    )
}