package com.base.andres.splashy.dummy

import com.base.andres.splashy.data.entity.ArtworkRemote
import com.base.andres.splashy.domain.entity.Artwork

class ArtworkDummyFactory {
    companion object {
        private const val SOME_ID = 1
        private const val SOME_TITLE = "Medusa"
        private const val SOME_ARTIST = "Bernini"
        private const val SOME_PICTURE_URL = "https://upload.wikimedia.org/wikipedia/commons/8/8e/Medusa_head_by_Gianlorenzo_Bernini_in_Musei_capitolini.jpg"

        fun getSomeArtwork(): Artwork {
            return Artwork(SOME_ID, SOME_TITLE, SOME_ARTIST, SOME_PICTURE_URL)
        }

        fun getSomeArtworks(): List<Artwork> {
            return listOf(getSomeArtwork(), getSomeArtwork(), getSomeArtwork())
        }

        fun getSomeArtworkRemote(): ArtworkRemote {
            return ArtworkRemote(SOME_ID, SOME_TITLE, SOME_ARTIST, SOME_PICTURE_URL)
        }
    }
}