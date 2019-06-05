package com.base.andres.splashy.domain.repository

import com.base.andres.splashy.domain.Either
import com.base.andres.splashy.domain.Failure
import com.base.andres.splashy.domain.datasource.ArtworkLocalDataSource
import com.base.andres.splashy.domain.datasource.ArtworkRemoteDataSource
import com.base.andres.splashy.domain.entity.Artwork

class ArtworkRepository(
    private val artworkRemoteDataSource: ArtworkRemoteDataSource,
    private val artworkLocalDataSource: ArtworkLocalDataSource
) {
    fun search(keywords: String): Either<Failure, List<Artwork>> {
        return artworkRemoteDataSource.search(keywords)
    }

    fun get(id: Int): Either<Failure, Artwork> {
        return when (val localResponse = artworkLocalDataSource.get(id)) {
            is Either.Left -> {
                when (val remoteResponse = artworkRemoteDataSource.get(id)) {
                    is Either.Left -> remoteResponse
                    is Either.Right -> {
                        artworkLocalDataSource.save(remoteResponse.b)
                        remoteResponse
                    }
                }
            }
            is Either.Right -> localResponse
        }
    }
}