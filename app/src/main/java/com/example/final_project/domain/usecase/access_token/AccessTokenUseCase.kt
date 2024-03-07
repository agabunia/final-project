package com.example.final_project.domain.usecase.access_token

import com.example.final_project.domain.repository.access_token.AccessTokenRepository
import javax.inject.Inject

class AccessTokenUseCase @Inject constructor(private val repository: AccessTokenRepository) {
    suspend operator fun invoke() = repository.getToken()
}