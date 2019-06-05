package com.base.andres.splashy.data.datasource

import com.base.andres.splashy.data.mapper.ArtworkMapper
import com.base.andres.splashy.domain.Either
import com.base.andres.splashy.domain.Failure
import com.base.andres.splashy.domain.datasource.ArtworkLocalDataSource
import com.base.andres.splashy.domain.entity.Artwork

class ArtworkRoomDataSource(
    private val artworkDao: ArtworkDao,
    private val artworkMapper: ArtworkMapper
): ArtworkLocalDataSource {
    override fun save(artwork: Artwork) {
        val artworkEntity = artworkMapper.map(artwork)
        artworkDao.save(artworkEntity)
    }

    override fun get(artworkId: Int): Either<Failure, Artwork> {
        val either: Either<Failure, Artwork>
        val artworkEntities = artworkDao.get(artworkId)
        if (artworkEntities.isNullOrEmpty()) {
            either = Either.Left(Failure.NotFoundError)
        } else {
            val artwork = artworkMapper.map(artworkEntities[0])
            if (artwork.isReady()) {
                either = Either.Right(artwork)
            } else {
                either = Either.Left(Failure.NotFoundError)
            }
        }
        return either
    }
}