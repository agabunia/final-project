package com.example.final_project.domain.local.usecase.db_manipulators

import com.example.final_project.domain.local.repository.wishlist.LocalProductRepository
import javax.inject.Inject

class DecreaseLocalProductQuantityUseCase @Inject constructor(
    private val localProductRepository: LocalProductRepository
) {
    suspend operator fun invoke(id: Int) = localProductRepository.decreaseProductQuantity(id = id)
}