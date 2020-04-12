package com.penguin.thebooklore.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.penguin.thebooklore.database.Cache
import com.penguin.thebooklore.database.MuseumDao
import com.penguin.thebooklore.model.Artwork
import com.penguin.thebooklore.network.RetrofitHelper

// injetar retrofit client aqui
class CollectionRepository private constructor(
        private val application: Application,
        private val cache: Cache,
        private val service: RetrofitHelper) {

    val reposErrors = MutableLiveData<String>()
    private var lastRequestedPage = 1
    val isRequestInProgress = MutableLiveData<Boolean>()

    fun search(query: String): LiveData<List<Artwork>> {

        lastRequestedPage = 1
        fetchNetwork(query)

        return cache.getArtwork(query)
    }

    private fun fetchNetwork(query: String) {
        if (isRequestInProgress.value == true) return
        isRequestInProgress.value = true

        service.getCollection(query, lastRequestedPage, NETWORK_PAGE_SIZE,
                { result ->
                    cache.insert(result) {
                        lastRequestedPage ++
                        isRequestInProgress.postValue(false)
                    }
                },
                { error ->
                    reposErrors.value = error
                    isRequestInProgress.postValue(false)
                })
    }

    companion object {
        private val TAG = CollectionRepository::class.java.simpleName
        private const val NETWORK_PAGE_SIZE = 10

        // For Singleton instantiation
        @Volatile
        private var instance: CollectionRepository? = null

        fun getInstance(application: Application, cache: Cache, retrofitHelper: RetrofitHelper) =
                instance ?: synchronized(this) {
                    instance ?: CollectionRepository(application, cache, retrofitHelper).also { instance = it }
                }
    }

}
