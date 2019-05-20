package com.base.andres.splashy.domain

class SearchArtworks
constructor(private val artworkRepository: ArtworkRepository): UseCase<List<Int>, String>() {
    override suspend fun execute(params: String): List<Int> {
        return artworkRepository.search(params)
    }
}