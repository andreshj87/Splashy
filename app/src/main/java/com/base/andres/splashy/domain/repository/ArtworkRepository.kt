package com.base.andres.splashy.domain.repository

import com.base.andres.splashy.data.remote.ArtworkApiService
import com.base.andres.splashy.data.mapper.ArtworkMapper
import com.base.andres.splashy.domain.Either
import com.base.andres.splashy.domain.Failure
import com.base.andres.splashy.domain.entity.Artwork

class ArtworkRepository(
    private val artworkApiService: ArtworkApiService,
    private val artworkMapper: ArtworkMapper
) {
    fun search(keywords: String): Either<Failure, List<Int>> {
        val apiResponse = artworkApiService.search(keywords).execute()
        return if (apiResponse.isSuccessful) {
            if (apiResponse.body() != null) {
                Either.Right(apiResponse.body()!!.results)
            } else {
                Either.Left(Failure.ApiError)
            }
        } else {
            Either.Left(Failure.ApiError)
        }
    }

    fun getArtwork(id: Int): Either<Failure, Artwork> {
        val apiResponse = artworkApiService.getArtwork(id).execute()
        return if (apiResponse.isSuccessful) {
            artworkMapper.map(apiResponse.body())
        } else {
            Either.Left(Failure.ApiError)
        }
    }
}