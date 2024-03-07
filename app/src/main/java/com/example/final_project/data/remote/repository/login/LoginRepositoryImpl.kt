package com.example.final_project.data.remote.repository.login

import com.example.final_project.data.common.Resource
import com.example.final_project.data.common.await
import com.example.final_project.domain.model.login.GetLogin
import com.example.final_project.domain.repository.login.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : LoginRepository {
    override suspend fun login(email: String, password: String): Flow<Resource<GetLogin>> {
        return flow {
            emit(Resource.Loading(loading = true))
            try {
                val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
                // ეს დიდი ალბათობით შეცდომაა, დატა ლეიერში ვიყენებ დომაინის მოდელს და რამენაირად
                // უნდა ჩავანაცვლოთ დატა ლეიერის მოდელით!!
                emit(Resource.Success(GetLogin(user = result.user!!)))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(errorMessage = e.message.toString()))
            }
            emit(Resource.Loading(loading = false))
        }
    }
}

// ეს რაღაც ერორ ჰენდლინგის იმპლემენტაცია ვნახე მაგრამ უკეთესია მოსაძებნი
// რამე ბესთ პრაქთისი იქნება

//} catch (e: FirebaseAuthException) {
//    val errorCode = e.errorCode
//    val errorMessage = when (errorCode) {
//        "ERROR_WRONG_PASSWORD" -> "Invalid password"
//        "ERROR_USER_NOT_FOUND" -> "User not found"
//        // Add more error code cases as needed
//        else -> "Authentication failed: ${e.message}"
//    }
//    emit(Resource.Error(errorMessage))
//}