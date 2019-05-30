package com.base.andres.splashy.domain.entity

import com.base.andres.splashy.domain.entity.PaperParcelArtwork
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class Artwork(
    val id: Int,
    val title: String? = null,
    val artist: String? = null,
    val coverPicture: String? = null
): PaperParcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelArtwork.CREATOR
    }

    fun isReady() = !title.isNullOrBlank()
}