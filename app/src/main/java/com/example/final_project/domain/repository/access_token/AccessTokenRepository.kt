package com.example.final_project.domain.repository.access_token

import com.example.final_project.data.common.Resource
import com.example.final_project.domain.model.access_token.GetAccessToken
import kotlinx.coroutines.flow.Flow

interface AccessTokenRepository {
    suspend fun getToken(): Flow<Resource<GetAccessToken>>
}
