package com.example.spotifyprojectmaster.data.network

import com.example.spotifyprojectmaster.data.model.ResponseToken
import retrofit2.http.*

interface SpotifyAuthService {

    @FormUrlEncoded
    @GET("/api/token")
    suspend fun getToken(@Field("grant_type") grantType: String): ResponseToken

}