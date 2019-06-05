package com.base.andres.splashy.domain.datasource

import com.base.andres.splashy.domain.Either
import com.base.andres.splashy.domain.Failure
import com.base.andres.splashy.domain.entity.Artwork

interface ArtworkLocalDataSource {
    fun save(artwork: Artwork)
    fun get(artworkId: Int): Either<Failure, Artwork>
}