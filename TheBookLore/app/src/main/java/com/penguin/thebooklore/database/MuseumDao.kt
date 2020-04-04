package com.penguin.thebooklore.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.penguin.thebooklore.model.Artwork

@Dao
interface MuseumDao {

    @Query("SELECT * FROM artwork_table")
    fun getArtwork(): LiveData<List<Artwork>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtwork(listArtwork : List<Artwork>)

    @Query("DELETE FROM artwork_table")
    fun deleteAllArtwork()

}