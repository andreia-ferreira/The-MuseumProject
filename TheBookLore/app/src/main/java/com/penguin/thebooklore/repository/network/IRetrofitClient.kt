package com.penguin.thebooklore.repository.network

import com.penguin.thebooklore.repository.network.model.CollectionResponse


import io.reactivex.Single
import retrofit2.http.GET

interface IRetrofitClient {

    @get:GET("en/collection?key=nX9svdcW&format=json&involvedMaker=Rembrandt+van+Rijn&ps=10&p=1&type=painting")
    val collection: Single<CollectionResponse>

}
