package com.base.andres.splashy.domain.usecase

import com.base.andres.splashy.domain.Either
import com.base.andres.splashy.domain.Failure
import com.base.andres.splashy.domain.entity.Artwork
import com.base.andres.splashy.domain.repository.ArtworkRepository

class SearchArtworks
constructor(private val artworkRepository: ArtworkRepository): UseCase<List<Artwork>, SearchArtworks.Params>() {
    override suspend fun execute(params: Params): Either<Failure, List<Artwork>> {
        return artworkRepository.search(params.keywords)
    }

    data class Params(val keywords: String)
}