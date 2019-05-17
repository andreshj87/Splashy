package com.base.andres.splashy.domain

import com.base.andres.splashy.data.ArtApiService
import com.base.andres.splashy.data.ArtSearchResponse
import retrofit2.Call

class ArtRepository(private val artApiService: ArtApiService) {
    fun search(keywords: String): Call<ArtSearchResponse> {
        return artApiService.search(keywords)
    }
}