package com.base.andres.splashy.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.base.andres.splashy.data.entity.ArtworkEntity

@Dao
interface ArtworkDao {
    @Insert
    fun save(artwork: ArtworkEntity)

    @Query("SELECT * FROM artwork WHERE id = :artworkId LIMIT 1")
    fun get(artworkId: Int): List<ArtworkEntity>

    @Query("SELECT * FROM artwork")
    fun getRecent(): List<ArtworkEntity>
}