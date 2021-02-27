package com.example.spotifyprojectmaster.data

import com.example.spotifyprojectmaster.data.model.ItemTrack
import com.example.spotifyprojectmaster.data.model.ResponseAllSongsDataModel
import com.example.spotifyprojectmaster.data.model.ResponseArtistDataModel
import com.example.spotifyprojectmaster.data.network.SpotifyNetwork
import java.lang.Exception

class SpotifyRepository {

    suspend fun getAllSongs(playListId: String): List<ItemTrack> {
        return SpotifyNetwork().getAllSongs(playListId).tracks.items
    }

    suspend fun getArtistInfo(artistId: String): ResponseArtistDataModel {
        val response = SpotifyNetwork().getArtistInfo(artistId)
        return if (response.id.isNotEmpty()) response else throw NoArtistException()
    }
}

class NoArtistException: Exception ()