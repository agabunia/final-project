package com.example.final_project.domain.usecase.datastore.theme

import com.example.final_project.domain.repository.datastore.DataStoreRepository
import com.example.final_project.domain.user_data_key.PreferenceKeys
import com.google.android.play.core.integrity.d
import javax.inject.Inject

class GetThemeDataStoreUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke() = dataStoreRepository.getThemeString(key = PreferenceKeys.THEME)
}