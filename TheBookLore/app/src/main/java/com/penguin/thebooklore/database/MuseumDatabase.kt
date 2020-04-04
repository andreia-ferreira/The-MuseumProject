package com.penguin.thebooklore.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.penguin.thebooklore.model.Artwork

@Database(
        entities = [Artwork::class],
        version = 1,
        exportSchema = false
)

abstract class MuseumDatabase : RoomDatabase() {

    abstract fun museumDao(): MuseumDao

    companion object {

        @Volatile
        private var INSTANCE: MuseumDatabase? = null

        fun getDatabase(context: Context): MuseumDatabase {

            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                        context.applicationContext,
                        MuseumDatabase::class.java,
                        "museumDatabase").build()
            }

        }

    }

}