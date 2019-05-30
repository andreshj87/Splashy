package com.base.andres.splashy.data.datasource

import com.base.andres.splashy.data.mapper.ArtworkMapper
import com.base.andres.splashy.data.remote.ArtworkApiService
import com.base.andres.splashy.domain.Either
import com.base.andres.splashy.domain.Failure
import com.base.andres.splashy.domain.datasource.ArtworkRemoteDataSource
import com.base.andres.splashy.domain.entity.Artwork

class ArtworkApiDataSource(
    private val artworkApiService: ArtworkApiService,
    private val artworkMapper: ArtworkMapper
) : ArtworkRemoteDataSource {
    override fun search(keywords: String): Either<Failure, List<Artwork>> {
        val response = artworkApiService.search(keywords).execute()
        return if (response.isSuccessful && response.body() != null) {
            val artworks = artworkMapper.map(response.body()!!.results)
            Either.Right(artworks)
        } else {
            Either.Left(Failure.ApiError)
        }
    }

    override fun get(id: Int): Either<Failure, Artwork> {
        val response = artworkApiService.getArtwork(id).execute()
        return if (response.isSuccessful) {
            val artwork = artworkMapper.map(response.body())
            if (artwork == null) {
                Either.Left(Failure.MappingError)
            } else {
                Either.Right(artwork)
            }
        } else {
            Either.Left(Failure.ApiError)
        }
    }
}