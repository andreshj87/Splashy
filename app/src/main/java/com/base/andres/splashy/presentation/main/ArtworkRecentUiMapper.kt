package com.base.andres.splashy.presentation.main

import com.base.andres.splashy.domain.entity.Artwork

class ArtworkRecentUiMapper {
    fun map(artworks: List<Artwork>): List<ArtworkRecentUi> {
        val artworkRecentUis = mutableListOf<ArtworkRecentUi>()
        artworks.forEach {
            artworkRecentUis.add(map(it))
        }
        return artworkRecentUis
    }

    fun map(artwork: Artwork): ArtworkRecentUi {
        return ArtworkRecentUi(artwork.title!!, artwork.coverPicture!!);
    }
}