package com.base.andres.splashy.domain.entity

import com.base.andres.splashy.domain.entity.PaperParcelArtwork
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class Artwork(
    val id: Int,
    val title: String,
    val artist: String,
    val coverPicture: String
): PaperParcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelArtwork.CREATOR
    }
}