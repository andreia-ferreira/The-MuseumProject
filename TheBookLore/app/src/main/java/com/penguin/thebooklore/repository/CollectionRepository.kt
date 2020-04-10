package com.penguin.thebooklore.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.penguin.thebooklore.R
import com.penguin.thebooklore.database.MuseumDao
import com.penguin.thebooklore.model.Artwork
import com.penguin.thebooklore.model.networkModel.CollectionResponse
import com.penguin.thebooklore.network.RetrofitHelper
import com.penguin.thebooklore.utils.mapper.ArtObjectMapper
import kotlinx.coroutines.*
import retrofit2.Response
import java.net.UnknownHostException

// injetar retrofit client aqui
class CollectionRepository private constructor(
        private val application: Application,
        private val museumDao: MuseumDao,
        private val retrofitHelper: RetrofitHelper)
    :   PageKeyedDataSource<Int, Result<List<Artwork>>>() {

    val reposErrors = MutableLiveData<String>()
    val artwork = MutableLiveData<List<Artwork>>()

    suspend fun refreshCollection(query: String,
                                  itemsPerPage: Int,
                                  page: Int): Boolean {

        try {

            val response = retrofitHelper.getCollection(query, itemsPerPage, page)

            if (response.isSuccessful) {
                val mappedObjects = ArtObjectMapper.mapListArtObject(response.body()?.networkArtworks)
                if (mappedObjects.isNotEmpty()) {
                    Log.d(TAG, "Found ${mappedObjects.size} results")
                    mappedObjects.map { it.type = query }
                    museumDao.insertArtwork(mappedObjects)
                    reposErrors.value = null
                } else {
                    reposErrors.value = application.getString(R.string.error_empty_search)
                }
            }
        } catch (e: UnknownHostException) {
            reposErrors.value = application.getString(R.string.error_service_call)
        }
        catch (e: Exception) {
            Log.e(TAG, "$e: ${e.message}")

        } finally {
            return true
        }
    }

    suspend fun getArtworkFromDatabase(searchText: String) {
        try {
            artwork.value = museumDao.getArtwork(searchText)
            Log.d(TAG, "Fetched ${artwork.value?.size} items")

        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
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

        fun getInstance(application: Application, museumDao: MuseumDao, retrofitHelper: RetrofitHelper) =
                instance ?: synchronized(this) {
                    instance ?: CollectionRepository(application, museumDao, retrofitHelper).also { instance = it }
                }
    }

}
