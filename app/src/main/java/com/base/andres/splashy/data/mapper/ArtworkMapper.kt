package com.base.andres.splashy.data.mapper

import com.base.andres.splashy.data.entity.ArtworkRemote
import com.base.andres.splashy.domain.entity.Artwork

class ArtworkMapper {
    fun map(artworkIds: List<Int>): List<Artwork> {
        val artworks: MutableList<Artwork> = mutableListOf()
        artworkIds.forEach {
            val artwork = Artwork(id = it)
            artworks.add(artwork)
        }
        return artworks
    }

    fun map(artworkRemote: ArtworkRemote?): Artwork? {
        return if (artworkRemote == null) {
            null
        } else {
            Artwork(
                artworkRemote.id,
                artworkRemote.title,
                artworkRemote.artist,
                artworkRemote.coverPicture
            )
        }
    }
}