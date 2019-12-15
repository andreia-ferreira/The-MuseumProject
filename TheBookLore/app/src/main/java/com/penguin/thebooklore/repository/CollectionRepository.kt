package com.penguin.thebooklore.repository

import androidx.paging.PageKeyedDataSource
import com.penguin.thebooklore.model.networkModel.CollectionResponse
import com.penguin.thebooklore.model.networkModel.NetworkArtObject
import com.penguin.thebooklore.model.networkModel.Result
import com.penguin.thebooklore.network.RetrofitHelper
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

object CollectionRepository : ICollectionRepository{

    override suspend fun getCollection(type: String, ps: Int, p: Int): CollectionResponse {
        return RetrofitHelper.getCollection(type, ps, p)
    }

    private val TAG = CollectionRepository::class.java.simpleName
}
