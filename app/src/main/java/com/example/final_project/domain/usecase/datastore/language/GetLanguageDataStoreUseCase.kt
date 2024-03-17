package com.example.final_project.domain.usecase.datastore.language

import com.example.final_project.domain.repository.datastore.DataStoreRepository
import com.example.final_project.domain.user_data_key.PreferenceKeys
import javax.inject.Inject

class GetLanguageDataStoreUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke() = dataStoreRepository.getLanguageString(key = PreferenceKeys.LANGUAGE)
}