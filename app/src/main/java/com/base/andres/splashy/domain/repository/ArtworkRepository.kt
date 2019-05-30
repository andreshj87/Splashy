package com.base.andres.splashy.domain.repository

import com.base.andres.splashy.domain.Either
import com.base.andres.splashy.domain.Failure
import com.base.andres.splashy.domain.datasource.ArtworkRemoteDataSource
import com.base.andres.splashy.domain.entity.Artwork

class ArtworkRepository(
    private val artworkRemoteDataSource: ArtworkRemoteDataSource
) {
    fun search(keywords: String): Either<Failure, List<Artwork>> {
        return artworkRemoteDataSource.search(keywords)
    }

    fun get(id: Int): Either<Failure, Artwork> {
        return artworkRemoteDataSource.get(id)
    }
}