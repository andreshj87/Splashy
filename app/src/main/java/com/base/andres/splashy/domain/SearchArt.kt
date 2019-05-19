package com.base.andres.splashy.domain

class SearchArt
constructor(private val artRepository: ArtRepository): UseCase<List<Int>, String>() {
    override suspend fun execute(params: String): List<Int> {
        return artRepository.search(params)
    }
}