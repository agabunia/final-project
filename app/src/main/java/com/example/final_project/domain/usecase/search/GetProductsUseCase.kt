package com.example.final_project.domain.usecase.search

import com.example.final_project.domain.repository.search.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke() = productRepository.getProducts()
}