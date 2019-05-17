package com.base.andres.splashy.domain

import com.base.andres.splashy.data.ArtSearchResponse
import retrofit2.Call

class SearchArt
constructor(private val artRepository: ArtRepository) {
    fun search(keywords: String): Call<ArtSearchResponse> {
        return artRepository.search(keywords)
    }
}