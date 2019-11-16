package com.penguin.thebooklore.repository

import androidx.lifecycle.MutableLiveData
import com.penguin.thebooklore.model.ArtObject
import com.penguin.thebooklore.repository.network.RetrofitHelper
import com.penguin.thebooklore.repository.network.model.CollectionResponse
import io.reactivex.Single

object CollectionRepository : ICollectionRepository {
    private val listArtObjects = MutableLiveData<List<ArtObject>>()
    private val TAG = CollectionRepository::class.java.simpleName


    override fun getCollection(): Single<CollectionResponse> {
        return RetrofitHelper.getInstance().collection
    }

}
