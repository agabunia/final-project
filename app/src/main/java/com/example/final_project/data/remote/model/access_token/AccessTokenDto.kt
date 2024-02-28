package com.example.final_project.data.remote.model.access_token

import com.squareup.moshi.Json

data class AccessTokenDto(
    @Json(name = "access_token")
    val accessToken: String,
    @Json(name = "token_type")
    val tokenType: String,
    @Json(name = "expires_in")
    val expiresIn: Int
)
