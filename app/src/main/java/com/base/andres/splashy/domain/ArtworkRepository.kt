package com.base.andres.splashy.domain

import com.base.andres.splashy.data.ArtworkApiService
import com.base.andres.splashy.data.ArtworkMapper

class ArtworkRepository(
    private val artworkApiService: ArtworkApiService,
    private val artworkMapper: ArtworkMapper
) {
    fun search(keywords: String): List<Int> {
        val results: List<Int>
        val apiResponse = artworkApiService.search(keywords).execute()
        results = if (apiResponse.isSuccessful) {
            if (apiResponse.body() != null) {
                apiResponse.body()!!.results
            } else {
                emptyList()
            }
        } else {
            emptyList()
        }
        return results
    }

    fun getArtwork(id: Int): Artwork? {
        val apiResponse = artworkApiService.getArtwork(id).execute()
        return if (apiResponse.isSuccessful) {
            artworkMapper.map(apiResponse.body())
        } else {
            null
        }
    }
}