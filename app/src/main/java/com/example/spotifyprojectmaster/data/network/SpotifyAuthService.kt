package com.example.spotifyprojectmaster.data.network

import com.example.spotifyprojectmaster.data.model.ResponseAllSongsDataModel
import com.example.spotifyprojectmaster.data.model.ResponseArtistDataModel
import com.example.spotifyprojectmaster.data.model.ResponseToken
import retrofit2.Call

import retrofit2.http.*

interface SpotifyAuthService {

    @FormUrlEncoded
    @POST("/api/token")
    suspend fun getToken(@Field("grant_type") grantType: String): ResponseToken

    @FormUrlEncoded
    @POST("/api/token")
    fun refreshToken(@Field("grant_type") grantType: String): Call<ResponseToken>

}