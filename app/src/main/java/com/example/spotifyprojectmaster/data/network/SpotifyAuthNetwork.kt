package com.example.spotifyprojectmaster.data.network

import androidx.viewbinding.BuildConfig
import com.example.spotifyprojectmaster.data.model.ResponseAllSongsDataModel
import com.example.spotifyprojectmaster.data.model.ResponseArtistDataModel
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SpotifyNetwork {

    lateinit var service: SpotifyService

    private fun loadRetrofit() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.spotify.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(createHttpClient())
                .build()

        service = retrofit.create(SpotifyService::class.java)
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
        val accessToken = "BQBXcKVk7ikJKjsgf5AVhTR_sH_VCiXcuYTIYX6qBwt5dKKo59iqq3qfvjYqCmkIGBsdlN_sl_jXF0ls2rg"
        builder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                    .addHeader("Authorization", Credentials.basic("a9254de97adc4e71848808621413c24d","af967b719bd040aeb0fed95d050e1fef"))
                            //"Bearer $accessToken")
                    .build()
            chain.proceed(request)
        }

        return builder.build()
    }


    suspend fun getAllSongs(playListId: String): ResponseAllSongsDataModel {
        loadRetrofit()
        return service.getSongs(playListId)
    }

    suspend fun getArtistInfo(artistId: String): ResponseArtistDataModel {
        loadRetrofit()
        return service.getArtist(artistId)
    }


}