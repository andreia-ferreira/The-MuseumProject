package com.penguin.thebooklore.network

import android.app.Application
import android.util.Log
import com.penguin.thebooklore.BuildConfig
import com.penguin.thebooklore.R
import com.penguin.thebooklore.model.Artwork
import com.penguin.thebooklore.model.networkModel.CollectionResponse
import com.penguin.thebooklore.utils.mapper.ArtObjectMapper

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper(private val mApplication: Application) {

    fun getCollection(query: String,
                      page: Int,
                      itemsPerPage: Int,
                      onSuccess: (repos: List<Artwork>) -> Unit,
                      onError: (error: String) -> Unit) {

        Log.d(TAG, "query: $query, page: $page, itemsPerPage: $itemsPerPage")

        client.getCollection(BuildConfig.API_KEY, query, itemsPerPage, page).enqueue(
                object : Callback<CollectionResponse> {
                    override fun onFailure(call: Call<CollectionResponse>, t: Throwable) {
                        Log.d(TAG, "fail to get data")
                        onError(t.message ?: mApplication.resources.getString(R.string.error_service_call))
                    }

                    override fun onResponse(call: Call<CollectionResponse>, response: Response<CollectionResponse>) {
                        Log.d(TAG, "got a response $response")

                        if (response.isSuccessful) {
                            val mappedObjects = ArtObjectMapper.mapListArtObject(response.body()?.networkArtworks)

                            if (mappedObjects.isNotEmpty()) {
                                mappedObjects.map { it.type = query }
                                onSuccess(mappedObjects)

                            } else {
                                onError(mApplication.resources.getString(R.string.error_empty_search))
                            }

                        } else {
                            onError(mApplication.resources.getString(R.string.error_service_call))
                        }
                    }
                }
        )
    }

    private val client: IRetrofitClient
        get() {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor { chain ->
                        val newRequest = chain.request().newBuilder()
                                .addHeader("Accept", "application/json")
                                .build()
                        chain.proceed(newRequest)
                    }
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()


            return retrofit.create(IRetrofitClient::class.java)

        }

    companion object {
        private val TAG = RetrofitHelper::class.java.simpleName

        // For Singleton instantiation
        @Volatile
        private var instance: RetrofitHelper? = null

        fun getInstance(application: Application) =
                instance ?: synchronized(this) {
                    instance ?: RetrofitHelper(application).also { instance = it }
                }
    }

}
