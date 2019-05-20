package com.base.andres.splashy.domain

class GetArtwork
constructor(private val artworkRepository: ArtworkRepository): UseCase<Artwork?, Int>() {
    override suspend fun execute(artworkId: Int): Artwork? {
        return artworkRepository.getArtwork(artworkId)
    }
}