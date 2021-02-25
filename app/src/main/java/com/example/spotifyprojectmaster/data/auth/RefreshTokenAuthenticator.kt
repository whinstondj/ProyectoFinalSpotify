package com.example.spotifyprojectmaster.data.auth

import com.example.spotifyprojectmaster.data.network.SpotifyAuthService
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class RefreshTokenAuthenticator: Authenticator {
    
    lateinit var service:SpotifyAuthService

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = refreshAccesToken()
        return response.request.newBuilder().header("Authorization", token).build()
    }

    private fun refreshAccesToken(): String {

    }
}