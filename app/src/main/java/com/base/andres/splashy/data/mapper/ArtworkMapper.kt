package com.base.andres.splashy.data.mapper

import com.base.andres.splashy.data.entity.ArtworkRemote
import com.base.andres.splashy.domain.Either
import com.base.andres.splashy.domain.Failure
import com.base.andres.splashy.domain.entity.Artwork

class ArtworkMapper {
    fun map(artworkRemote: ArtworkRemote?): Either<Failure, Artwork> {
        return if (artworkRemote == null) {
            Either.Left(Failure.MappingError)
        } else {
            val artwork = Artwork(
                artworkRemote.id,
                artworkRemote.title,
                artworkRemote.artist,
                artworkRemote.coverPicture
            )
            return Either.Right(artwork)
        }
    }
}