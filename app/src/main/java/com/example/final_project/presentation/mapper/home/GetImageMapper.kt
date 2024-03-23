package com.example.final_project.presentation.mapper.home

import com.example.final_project.domain.remote.model.home.GetImage
import com.example.final_project.presentation.model.home.ImageModel

fun GetImage.toPresenter(): ImageModel {
    return ImageModel(
        images = images
    )
}