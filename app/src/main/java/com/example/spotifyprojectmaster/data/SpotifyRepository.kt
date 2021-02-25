package com.example.spotifyprojectmaster.data

import com.example.spotifyprojectmaster.data.model.ItemTrack
import com.example.spotifyprojectmaster.data.model.ResponseAllSongsDataModel
import com.example.spotifyprojectmaster.data.model.ResponseArtistDataModel
import com.example.spotifyprojectmaster.data.network.SpotifyNetwork

class SpotifyRepository {

    suspend fun getAllSongs(playListId: String): List<ItemTrack> {
        return SpotifyNetwork().getAllSongs(playListId).tracks.items
    }

    suspend fun getArtistInfo(artistId: String): ResponseArtistDataModel {
        return SpotifyNetwork().getArtistInfo(artistId)
    }
}