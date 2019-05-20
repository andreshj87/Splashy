package com.base.andres.splashy.data

import com.base.andres.splashy.domain.Artwork

class ArtworkMapper {
    fun map(artworkRemote: ArtworkRemote?): Artwork? {
        return if (artworkRemote == null) {
            null
        } else {
            Artwork(artworkRemote.id, artworkRemote.title, artworkRemote.artist, artworkRemote.coverPicture)
        }
    }
}