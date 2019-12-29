package com.penguin.thebooklore.repository

import android.app.Application
import com.penguin.thebooklore.R
import com.penguin.thebooklore.model.Artwork
import com.penguin.thebooklore.utils.mapper.ArtObjectMapper
import com.penguin.thebooklore.model.networkModel.Result
import com.penguin.thebooklore.network.RetrofitHelper

// injetar retrofit client aqui
class CollectionRepository private constructor(
        private val application: Application)
    : ICollectionRepository {

    override suspend fun getCollection(type: String, ps: Int, p: Int): Result<List<Artwork>> {
        val response = RetrofitHelper.getCollection(type, ps, p)
        return if (response != null) {
            Result.Success(ArtObjectMapper.mapListArtObject(response.networkArtworks))
        } else {
            Result.Failure(Exception(application.resources.getString(R.string.error_service_call)))
        }
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

}
