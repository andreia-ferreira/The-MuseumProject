package com.penguin.thebooklore.network

import com.penguin.thebooklore.model.networkModel.CollectionResponse
import retrofit2.Call
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofitClient {

    @GET("en/collection")
    suspend fun getCollection(@Query("key") key: String,
                      @Query("type") type: String,
                      @Query("ps") resultsPerPage: Int,
                      @Query("p") page: Int)
            : Response<CollectionResponse?>
}
