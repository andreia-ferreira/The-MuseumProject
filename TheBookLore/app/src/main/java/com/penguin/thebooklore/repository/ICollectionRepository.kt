package com.penguin.thebooklore.repository

import com.penguin.thebooklore.model.ArtObject
import com.penguin.thebooklore.model.networkModel.Result

interface ICollectionRepository {
    suspend fun getCollection(type: String, ps: Int, p: Int): Result<List<ArtObject>>
}
