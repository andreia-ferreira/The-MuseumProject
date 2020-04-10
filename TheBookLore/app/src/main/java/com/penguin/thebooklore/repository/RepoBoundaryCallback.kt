package com.penguin.thebooklore.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.penguin.thebooklore.model.networkModel.NetworkArtwork
import com.penguin.thebooklore.network.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


// TODO implement paging library
class RepoBoundaryCallback(private val query: String, private val coroutineScope: CoroutineScope):
        PagedList.BoundaryCallback<NetworkArtwork>() {

    private var lastRequestedPage = 1

    private val networkError = MutableLiveData<String>()
    val networkErrors: LiveData<String>
        get() = networkErrors

    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        getMoreData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: NetworkArtwork) {
        getMoreData()
    }

    private fun getMoreData() {
        if (isRequestInProgress) return

        isRequestInProgress = true
        coroutineScope.launch {
//            RetrofitHelper.getCollection(query, lastRequestedPage, REQUEST_PAGE_SIZE)
            lastRequestedPage++
        }
    }

    companion object {
        private const val REQUEST_PAGE_SIZE = 10
    }

}