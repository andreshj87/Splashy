package com.base.andres.splashy.data.entity

import com.squareup.moshi.Json

data class ArtworkSearchResponse(
    @field:Json(name = "total") val total: Int,
    @field:Json(name = "objectIDs") val results: List<Int>
)