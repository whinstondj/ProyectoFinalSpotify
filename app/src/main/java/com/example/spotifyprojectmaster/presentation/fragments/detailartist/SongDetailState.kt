package com.example.spotifyprojectmaster.presentation.fragments.detailartist

import com.example.spotifyprojectmaster.data.model.ResponseArtistDataModel
import java.io.Serializable

data class SongDetailState(
    val artistDetail: ResponseArtistDataModel? = null
) : Serializable