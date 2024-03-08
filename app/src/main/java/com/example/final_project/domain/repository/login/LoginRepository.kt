package com.example.final_project.domain.repository.login

import com.example.final_project.data.common.Resource
import com.example.final_project.domain.model.login.GetLogin
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(email: String, password: String): Flow<Resource<GetLogin>>
}
