package com.base.andres.splashy.domain.usecase

import com.base.andres.splashy.domain.Either
import com.base.andres.splashy.domain.Failure
import com.base.andres.splashy.domain.entity.Artwork
import com.base.andres.splashy.domain.repository.ArtworkRepository

class GetRecentArtworks
constructor(private val artworkRepository: ArtworkRepository): UseCase<List<Artwork>, UseCase.None>() {
    override suspend fun execute(params: None): Either<Failure, List<Artwork>> {
        return artworkRepository.getRecent()
    }
}