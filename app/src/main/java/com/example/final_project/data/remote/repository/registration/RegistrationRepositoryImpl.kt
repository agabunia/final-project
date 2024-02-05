package com.example.final_project.data.remote.repository.registration

import com.example.final_project.data.common.Resource
import com.example.final_project.domain.model.registration.GetRegistration
import com.example.final_project.domain.repository.registration.RegistrationRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : RegistrationRepository {
    override suspend fun registration(
        email: String,
        password: String
    ): Flow<Resource<GetRegistration>> {
        return flow {
            emit(Resource.Loading(loading = true))
            try {
                val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                // აქაც გადასაკეთებელი იქნება მოდელი რომ დეითასი იყოს და არა დომეინის!!
                emit(Resource.Success(GetRegistration(user = result.user!!)))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(errorMessage = e.message.toString()))
            }
            emit(Resource.Loading(loading = false))
        }
    }
}
