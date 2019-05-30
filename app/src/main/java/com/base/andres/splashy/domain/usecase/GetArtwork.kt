package com.base.andres.splashy.domain.usecase

import com.base.andres.splashy.domain.Either
import com.base.andres.splashy.domain.Failure
import com.base.andres.splashy.domain.entity.Artwork
import com.base.andres.splashy.domain.repository.ArtworkRepository

class GetArtwork
constructor(private val artworkRepository: ArtworkRepository): UseCase<Artwork, GetArtwork.Params>() {
    override suspend fun execute(params: Params): Either<Failure, Artwork> {
        return artworkRepository.get(params.artworkId)
    }

    data class Params(val artworkId: Int)
}