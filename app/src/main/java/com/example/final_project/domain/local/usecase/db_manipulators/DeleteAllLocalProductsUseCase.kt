package com.example.final_project.domain.local.usecase.db_manipulators

import com.example.final_project.domain.local.repository.wishlist.LocalProductRepository
import javax.inject.Inject

class DeleteAllLocalProductsUseCase @Inject constructor(
    private val localProductRepository: LocalProductRepository
) {
    suspend operator fun invoke() = localProductRepository.deleteAllProducts()
}