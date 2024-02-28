package com.example.final_project.data.remote.repository.access_token

import android.util.Base64
import android.util.Log.d
import com.example.final_project.data.common.HandleResponse
import com.example.final_project.data.common.Resource
import com.example.final_project.data.remote.mapper.access_token.toDomain
import com.example.final_project.data.remote.mapper.base.asResource
import com.example.final_project.data.remote.service.access_token.AccessTokenService
import com.example.final_project.domain.model.access_token.GetAccessToken
import com.example.final_project.domain.repository.access_token.AccessTokenRepository
import kotlinx.coroutines.flow.Flow
import java.net.URLEncoder
import javax.inject.Inject

class AccessTokenRepositoryImpl @Inject constructor(
    private val handleResponse: HandleResponse,
    private val accessTokenService: AccessTokenService
) : AccessTokenRepository {
    override suspend fun getToken(): Flow<Resource<GetAccessToken>> {
        return handleResponse.safeApiCall {
            d("testTestService", "safeApiCall is invoked")
            accessTokenService.getAccessToken(
                authorization = authorizationHeader,
            )
        }.asResource {
            d("testTestService", it.accessToken)
            it.toDomain()
        }
    }

    fun encodeCredentials(clientId: String, clientSecret: String): String {
        val credentials = "$clientId:$clientSecret"
        d("testTestService", "encodeCredentials is invoked")
        return Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
    }

    var clientId = "90451e7919f24d10ac3b1eff3f6f4c10"
    var clientSecret = "03801f53a6f74f178d3a8b0d4dbbfae0"

    val clientIdEncoded = URLEncoder.encode(clientId, "UTF-8")
    val clientSecretEncoded = URLEncoder.encode(clientSecret, "UTF-8")
    val authorizationHeader = "Basic ${encodeCredentials(clientIdEncoded, clientSecretEncoded)}"

}
