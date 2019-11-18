package com.penguin.thebooklore.repository

import androidx.paging.PageKeyedDataSource
import com.penguin.thebooklore.model.networkModel.CollectionResponse
import com.penguin.thebooklore.model.networkModel.NetworkArtObject
import com.penguin.thebooklore.network.RetrofitHelper
import io.reactivex.Single

object CollectionRepository : ICollectionRepository, PageKeyedDataSource<Long, NetworkArtObject>() {

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, NetworkArtObject>) {
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, NetworkArtObject>) {

    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, NetworkArtObject>) {

    }

    override fun getCollection(type: String, ps: Int, p: Int): Single<CollectionResponse> {
        return RetrofitHelper.getCollection(type, ps, p)
    }

    private val TAG = CollectionRepository::class.java.simpleName
}
