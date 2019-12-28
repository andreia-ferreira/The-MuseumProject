package com.penguin.thebooklore.repository

import android.app.Application
import android.util.Log
import com.penguin.thebooklore.R
import com.penguin.thebooklore.model.ArtObject
import com.penguin.thebooklore.model.interfacee.IMapper
import com.penguin.thebooklore.model.interfacee.INullableInputListMapper
import com.penguin.thebooklore.model.networkModel.NetworkArtObject
import com.penguin.thebooklore.model.networkModel.Result
import com.penguin.thebooklore.network.RetrofitHelper

// injetar retrofit client aqui
class CollectionRepository private constructor(private val application: Application)
    : ICollectionRepository, INullableInputListMapper<NetworkArtObject, ArtObject> {

    override suspend fun getCollection(type: String, ps: Int, p: Int): Result<List<ArtObject>> {
        val response = RetrofitHelper.getCollection(type, ps, p)
        return if (response != null) {
            Result.Success(map(response.networkArtObjects))
        } else {
            Result.Failure(Exception(application.resources.getString(R.string.error_service_call)))
        }
    }

    override fun map(input: List<NetworkArtObject>?): List<ArtObject> {
        return input?.map {
            ArtObject(it.id ?: "Unknown id",
                    it.title ?: "Untitled",
                    it.hasImage ?: false,
                    it.networkWebImage?.url ?: "Unknown url")
        } ?: emptyList()
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
