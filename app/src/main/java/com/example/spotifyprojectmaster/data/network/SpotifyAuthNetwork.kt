package com.example.spotifyprojectmaster.data.network

import androidx.viewbinding.BuildConfig
import com.example.spotifyprojectmaster.data.auth.RefreshTokenAuthenticator
import com.example.spotifyprojectmaster.data.model.ResponseAllSongsDataModel
import com.example.spotifyprojectmaster.data.model.ResponseArtistDataModel
import com.example.spotifyprojectmaster.data.model.ResponseToken
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SpotifyAuthNetwork {

    lateinit var service: SpotifyAuthService

    private fun loadRetrofit() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://accounts.spotify.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(createHttpClient())
                .build()

        service = retrofit.create(SpotifyAuthService::class.java)
    }

    private fun createHttpClient(): OkHttpClient {
        // Create OkHttpClient
        val builder = OkHttpClient.Builder()
                .connectTimeout(90L, TimeUnit.SECONDS)
                .readTimeout(90L, TimeUnit.SECONDS)
                .writeTimeout(90L, TimeUnit.SECONDS)

        // Logger interceptor
        val loggerInterceptor = HttpLoggingInterceptor()
        loggerInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        builder.addInterceptor(loggerInterceptor)


        // App token
        builder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                    .addHeader("Authorization", Credentials.basic("a9254de97adc4e71848808621413c24d","af967b719bd040aeb0fed95d050e1fef"))
                    .build()
            chain.proceed(request)
        }

        builder.authenticator(RefreshTokenAuthenticator())
                .connectTimeout(90L, TimeUnit.SECONDS)
                .followRedirects(false)
                .followSslRedirects(false)

        return builder.build()
    }


    suspend fun getAuthToken(): ResponseToken {
        loadRetrofit()
        return service.getToken("client_credentials")
    }


}