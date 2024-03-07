package com.example.final_project.domain.model.access_token

data class GetAccessToken(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int
)
