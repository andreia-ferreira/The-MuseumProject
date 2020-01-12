package com.penguin.thebooklore.repository

import android.app.Application
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.penguin.thebooklore.R
import com.penguin.thebooklore.model.Artwork
import com.penguin.thebooklore.network.RetrofitHelper
import com.penguin.thebooklore.utils.mapper.ArtObjectMapper

// injetar retrofit client aqui
class CollectionRepository private constructor(
        private val application: Application)
    :   PageKeyedDataSource<Int, Result<List<Artwork>>>() {


    suspend fun getCollection(query: String,
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

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: CollectionRepository? = null

        fun getInstance(application: Application) =
                instance ?: synchronized(this) {
                    instance ?: CollectionRepository(application).also { instance = it }
                }

        private val TAG = CollectionRepository::class.java.simpleName
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Result<List<Artwork>>>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result<List<Artwork>>>) {

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result<List<Artwork>>>) {

    }

}
