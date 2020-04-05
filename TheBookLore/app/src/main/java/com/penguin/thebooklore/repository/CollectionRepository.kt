package com.penguin.thebooklore.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.PageKeyedDataSource
import com.penguin.thebooklore.R
import com.penguin.thebooklore.database.MuseumDao
import com.penguin.thebooklore.database.MuseumDatabase
import com.penguin.thebooklore.model.Artwork
import com.penguin.thebooklore.network.RetrofitHelper
import com.penguin.thebooklore.utils.mapper.ArtObjectMapper

// injetar retrofit client aqui
class CollectionRepository private constructor(
        private val application: Application,
        private val museumDao: MuseumDao)
    :   PageKeyedDataSource<Int, Result<List<Artwork>>>() {

    suspend fun refreshCollection(query: String,
                                  page: Int,
                                  itemsPerPage: Int,
                                  onSuccess: (result: List<Artwork>) -> Unit,
                                  onError: (error: String) -> Unit) {

        val response = RetrofitHelper.getCollection(query, itemsPerPage, page)
        if (response.isSuccessful) {
            val mappedObjects = ArtObjectMapper.mapListArtObject(response.body()?.networkArtworks)

            onSuccess(mappedObjects)
            return
        }
        Log.d(TAG, response.errorBody().toString())
        onError(application.resources.getString(R.string.error_service_call))
    }

    // convert to suspend
    suspend fun insertArtwork(listArt: List<Artwork>) {
        museumDao.insertArtwork(listArt)
    }

    suspend fun getArtwork(searchText: String): List<Artwork> {
        return museumDao.getArtwork(searchText)
    }

    // TODO implement paging library
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Result<List<Artwork>>>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result<List<Artwork>>>) {

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result<List<Artwork>>>) {

    }

    companion object {
        private val TAG = CollectionRepository::class.java.simpleName

        // For Singleton instantiation
        @Volatile
        private var instance: CollectionRepository? = null

        fun getInstance(application: Application, museumDao: MuseumDao) =
                instance ?: synchronized(this) {
                    instance ?: CollectionRepository(application, museumDao).also { instance = it }
                }
    }

}
