package com.base.andres.splashy.data

import com.squareup.moshi.Json

data class ArtSearchResponse(
    @field:Json(name = "total") val total: Int,
    @field:Json(name = "objectIDs") val results: List<Int>
)