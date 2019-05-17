package com.base.andres.splashy.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtApiService {
    companion object {
        private const val BASE_URL = "https://collectionapi.metmuseum.org/public/collection/v1"
    }

    @GET("$BASE_URL/search")
    fun search(@Query("q") keywords: String): Call<ArtSearchResponse>
}