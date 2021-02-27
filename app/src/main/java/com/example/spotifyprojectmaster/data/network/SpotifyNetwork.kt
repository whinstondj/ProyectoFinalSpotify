package com.example.spotifyprojectmaster.data.network

import androidx.viewbinding.BuildConfig
import com.example.spotifyprojectmaster.data.model.ResponseAllSongsDataModel
import com.example.spotifyprojectmaster.data.model.ResponseArtistDataModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SpotifyNetwork {

    lateinit var service: SpotifyService

    private fun loadRetrofit(authToken: String) {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.spotify.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(createHttpClient(authToken))
                .build()

        service = retrofit.create(SpotifyService::class.java)
    }

    private fun createHttpClient(authToken: String): OkHttpClient {
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
                    .addHeader("Authorization", "Bearer $authToken")
                    .build()
            chain.proceed(request)
        }

        return builder.build()
    }


    suspend fun getAllSongs(playListId: String): ResponseAllSongsDataModel {
        val authToken = SpotifyAuthNetwork().getAuthToken().access_token
        loadRetrofit(authToken)
        return service.getSongs(playListId)
    }

    suspend fun getArtistInfo(artistId: String): ResponseArtistDataModel {
        val authToken = SpotifyAuthNetwork().getAuthToken().access_token
        loadRetrofit(authToken)
        return service.getArtist(artistId)
    }


}