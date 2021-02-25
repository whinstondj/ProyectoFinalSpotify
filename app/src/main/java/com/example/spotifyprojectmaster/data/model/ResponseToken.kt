package com.example.spotifyprojectmaster.data.model

data class ResponseToken(
    val access_token: String,
    val expires_in: Int,
    val scope: String,
    val token_type: String
)