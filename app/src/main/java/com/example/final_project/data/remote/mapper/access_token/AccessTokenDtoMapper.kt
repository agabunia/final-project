package com.example.final_project.data.remote.mapper.access_token

import com.example.final_project.data.remote.model.access_token.AccessTokenDto
import com.example.final_project.domain.model.access_token.GetAccessToken

fun AccessTokenDto.toDomain(): GetAccessToken {
    return GetAccessToken(
        accessToken = accessToken,
        tokenType = tokenType,
        expiresIn = expiresIn
    )
}
