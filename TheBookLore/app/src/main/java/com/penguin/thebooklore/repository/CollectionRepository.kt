package com.penguin.thebooklore.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
        private val retrofitHelper: RetrofitHelper) {

    val reposErrors = MutableLiveData<String>()

    suspend fun refreshCollection(query: String,
                                  itemsPerPage: Int,
                                  page: Int): Boolean {

        try {
            reposErrors.value = null
            val response = retrofitHelper.getCollection(query, itemsPerPage, page)

            if (response.isSuccessful) {
                val mappedObjects = ArtObjectMapper.mapListArtObject(response.body()?.networkArtworks)
                if (mappedObjects.isNotEmpty()) {
                    Log.d(TAG, "Found ${mappedObjects.size} results")
                    mappedObjects.map { it.type = query }
                    museumDao.insertArtwork(mappedObjects)
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

    fun getArtworkFromDatabase(searchText: String): LiveData<List<Artwork>> {
        val fetchedData = museumDao.getArtwork(searchText)
        Log.d(TAG, "Fetched ${fetchedData.value?.size} items")

        return fetchedData
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
