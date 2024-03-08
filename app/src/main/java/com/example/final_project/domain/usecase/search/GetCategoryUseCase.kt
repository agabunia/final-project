package com.example.final_project.domain.usecase.search

import com.example.final_project.domain.repository.search.CategoryRepository
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke() = categoryRepository.getCategory()
}