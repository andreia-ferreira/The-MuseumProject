package com.penguin.thebooklore.database

import android.util.Log
import androidx.lifecycle.LiveData
import com.penguin.thebooklore.model.Artwork
import java.util.concurrent.Executor

class Cache(
        private val museumDao: MuseumDao,
        private val ioExecutor: Executor) {

    fun insert(results: List<Artwork>, finish: () -> Unit) {
        ioExecutor.execute{
            Log.d(TAG, "inserting ${results.size} repos")
            museumDao.insertArtwork(results)
            finish()
        }
    }

    fun getArtwork(search: String): LiveData<List<Artwork>> {
        return museumDao.getArtwork(search)
    }

    companion object {
        var TAG: String = Cache::class.java.simpleName
    }

}