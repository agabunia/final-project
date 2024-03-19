package com.example.final_project.data.local.mapper

import com.example.final_project.data.local.entity.ProductEntity
import com.example.final_project.domain.remote.model.product.GetProductDetailed
import com.example.final_project.domain.local.model.wishlist.GetWishlistProduct

fun ProductEntity.toDomain(): GetWishlistProduct {
    return GetWishlistProduct(
        id = id,
        title = title,
        description = description,
        price = price,
        thumbnail = thumbnail,
        quantity = quantity,
        sumPrice = sumPrice
    )
}