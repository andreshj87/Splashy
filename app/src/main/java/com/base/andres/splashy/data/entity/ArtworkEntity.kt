package com.base.andres.splashy.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artwork")
data class ArtworkEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "artist") val artist: String?,
    @ColumnInfo(name = "cover_picture") val coverPicture: String?
)