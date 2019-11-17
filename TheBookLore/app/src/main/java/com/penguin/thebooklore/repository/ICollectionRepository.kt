package com.penguin.thebooklore.repository

import com.penguin.thebooklore.repository.network.model.CollectionResponse

import io.reactivex.Single

interface ICollectionRepository {
    fun getCollection(type: String, ps: Int, p: Int): Single<CollectionResponse>
}
