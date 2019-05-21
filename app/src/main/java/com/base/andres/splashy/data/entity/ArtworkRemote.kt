package com.base.andres.splashy.data.entity

import com.squareup.moshi.Json

data class ArtworkRemote(
    @field:Json(name = "objectID") val id: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "artistDisplayName") val artist: String,
    @field:Json(name = "primaryImage") val coverPicture: String
)