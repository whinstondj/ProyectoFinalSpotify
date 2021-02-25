package com.example.spotifyprojectmaster.presentation.fragments.songlist

import com.example.spotifyprojectmaster.data.model.ItemTrack
import java.io.Serializable

data class SongListState(
    val songList: List<ItemTrack> = listOf()
) : Serializable