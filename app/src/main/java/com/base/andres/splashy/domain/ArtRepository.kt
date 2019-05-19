package com.base.andres.splashy.domain

import com.base.andres.splashy.data.ArtApiService

class ArtRepository(private val artApiService: ArtApiService) {
    fun search(keywords: String): List<Int> {
        val results: List<Int>
        val apiResponse = artApiService.search(keywords).execute()
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
}