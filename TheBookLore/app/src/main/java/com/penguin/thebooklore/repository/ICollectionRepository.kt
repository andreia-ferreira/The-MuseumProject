package com.penguin.thebooklore.repository

import com.penguin.thebooklore.model.ArtObject
import com.penguin.thebooklore.repository.network.model.CollectionResponse

import io.reactivex.Single

interface ICollectionRepository {
    val collection: Single<CollectionResponse>
}
