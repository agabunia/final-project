package com.example.final_project.domain.usecase.datastore

import com.example.final_project.domain.repository.datastore.DataStoreRepository
import com.example.final_project.domain.user_data_key.PreferenceKeys
import javax.inject.Inject

class ReadDataStoreUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke() = dataStoreRepository.readString(key = PreferenceKeys.TOKEN)
}