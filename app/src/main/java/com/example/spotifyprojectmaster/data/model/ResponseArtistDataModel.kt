package com.example.spotifyprojectmaster.data.model

data class ResponseArtistDataModel(
    val external_urls: ExternalUrls,
    val followers: Followers,
    val genres: List<String>,
    val href: String,
    val id: String,
    val images: List<ArtistImage>,
    val name: String,
    val popularity: Int,
    val type: String,
    val uri: String
)

data class ArtistImage(
    val height: Int,
    val url: String,
    val width: Int
)