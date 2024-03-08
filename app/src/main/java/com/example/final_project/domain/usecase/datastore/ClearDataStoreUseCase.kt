package com.example.final_project.domain.usecase.datastore

import com.example.final_project.domain.repository.datastore.DataStoreRepository
import javax.inject.Inject

class ClearDataStoreUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke() = dataStoreRepository.clear()
}