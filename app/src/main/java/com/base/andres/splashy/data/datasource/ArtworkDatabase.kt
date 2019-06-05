package com.base.andres.splashy.data.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.base.andres.splashy.data.entity.ArtworkEntity

@Database(entities = [ArtworkEntity::class], version = 1)
abstract class ArtworkDatabase: RoomDatabase() {
    abstract fun ArtworkDao(): ArtworkDao

    companion object {
        private val DATABASE_NAME = "artwork_db"
        @Volatile private var INSTANCE: ArtworkDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: createDatabase(context).also { INSTANCE = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context, ArtworkDatabase::class.java, DATABASE_NAME).build()
    }
}