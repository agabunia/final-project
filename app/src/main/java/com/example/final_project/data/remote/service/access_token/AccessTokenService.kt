package com.example.final_project.data.remote.service.access_token

import android.util.Base64
import com.example.final_project.data.remote.model.access_token.AccessTokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import java.nio.charset.StandardCharsets
import kotlin.io.encoding.ExperimentalEncodingApi

interface AccessTokenService {
    @POST("https://accounts.spotify.com/api/token")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Header("Authorization") authorization: String,
        @Field("grant_type") grantType: String = "client_credentials",
        @Field("client_id") clientId: String = "90451e7919f24d10ac3b1eff3f6f4c10",
        @Field("client_secret") clientSecret: String = "03801f53a6f74f178d3a8b0d4dbbfae0"
    ): Response<AccessTokenDto>
}
