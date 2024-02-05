package com.example.final_project.domain.repository.registration

import com.example.final_project.data.common.Resource
import com.example.final_project.domain.model.registration.GetRegistration
import kotlinx.coroutines.flow.Flow

interface RegistrationRepository {
    suspend fun registration(email: String, password: String): Flow<Resource<GetRegistration>>
}
