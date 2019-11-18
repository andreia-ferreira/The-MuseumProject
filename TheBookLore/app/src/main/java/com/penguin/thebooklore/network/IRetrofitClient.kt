package com.penguin.thebooklore.network

import com.penguin.thebooklore.model.networkModel.CollectionResponse


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofitClient {

    @GET("en/collection")
    fun getCollection(@Query("key") key: String,
                      @Query("type") type: String,
                      @Query("ps") resultsPerPage: Int,
                      @Query("p") page: Int)
            : Single<CollectionResponse>

}
