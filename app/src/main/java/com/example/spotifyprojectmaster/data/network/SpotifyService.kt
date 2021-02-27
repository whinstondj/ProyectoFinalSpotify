package com.example.spotifyprojectmaster.data.network

import com.example.spotifyprojectmaster.data.model.ResponseAllSongsDataModel
import com.example.spotifyprojectmaster.data.model.ResponseArtistDataModel
import com.example.spotifyprojectmaster.data.model.ResponseToken
import retrofit2.http.*

interface SpotifyService {


    @GET("v1/playlists/{playlistId}/")
    suspend fun getSongs(@Path("playlistId") playListId: String): ResponseAllSongsDataModel

    @GET("v1/artists/{artistId}")
    suspend fun getArtist(@Path("artistId") artistId: String): ResponseArtistDataModel
}