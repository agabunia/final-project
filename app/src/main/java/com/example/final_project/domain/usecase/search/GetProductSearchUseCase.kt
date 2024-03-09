package com.example.final_project.domain.usecase.search

import com.example.final_project.domain.repository.search.ProductSearchRepository
import javax.inject.Inject

class GetProductSearchUseCase @Inject constructor(
    private val repository: ProductSearchRepository
) {
    suspend operator fun invoke(search: String) = repository.searchProduct(search = search)
}