package com.base.andres.splashy.domain.usecase

import com.base.andres.splashy.domain.Either
import com.base.andres.splashy.domain.Failure
import com.base.andres.splashy.domain.repository.ArtworkRepository

class SearchArtworks
constructor(private val artworkRepository: ArtworkRepository): UseCase<List<Int>, SearchArtworks.Params>() {
    override suspend fun execute(params: Params): Either<Failure, List<Int>> {
        return artworkRepository.search(params.keywords)
    }

    data class Params(val keywords: String)
}