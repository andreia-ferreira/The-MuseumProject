package com.penguin.thebooklore.repository

import com.penguin.thebooklore.model.networkModel.CollectionResponse

interface ICollectionRepository {
    suspend fun getCollection(type: String, ps: Int, p: Int): CollectionResponse?
}
