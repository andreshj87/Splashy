package com.base.andres.splashy.domain.datasource

import com.base.andres.splashy.domain.Either
import com.base.andres.splashy.domain.Failure
import com.base.andres.splashy.domain.entity.Artwork

interface ArtworkRemoteDataSource {
    fun search(keywords: String): Either<Failure, List<Artwork>>
    fun get(id: Int): Either<Failure, Artwork>
}