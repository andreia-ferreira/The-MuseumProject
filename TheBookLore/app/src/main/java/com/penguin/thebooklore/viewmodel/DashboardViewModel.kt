package com.penguin.thebooklore.viewmodel

import android.app.Application
import android.util.Log

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.penguin.thebooklore.R

import com.penguin.thebooklore.model.networkModel.NetworkArtObject
import com.penguin.thebooklore.repository.CollectionRepository
import com.penguin.thebooklore.model.networkModel.CollectionResponse
import kotlinx.coroutines.launch

import java.lang.Exception

class DashboardViewModel(application: Application) : BaseViewModel(application) {
    val listArtObjects = MutableLiveData<List<NetworkArtObject>>()
    private val collectionRepository: CollectionRepository = CollectionRepository
    var currentPage = 1

    init {
        getCollection()
    }

    fun getCollection() {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val collectionResponse = collectionRepository.getCollection("painting", 10, currentPage)
                processDataResponse(collectionResponse)
            } catch (e: Exception) {
                isError.value = Exception(getApplication<Application>().resources.getString(R.string.error_service_call))
            }
            isLoading.value = false
        }
    }

    private fun processDataResponse(response: CollectionResponse?) {
        Log.d(TAG, "Success!")
        response?.networkArtObjects?.let { artObjects ->

            if (artObjects.isNotEmpty()) {
                Log.d(TAG, "Found " + artObjects.size + " results.")
                listArtObjects.value = artObjects
                currentPage ++
                return
            }

        }
        isError.value = Exception(getApplication<Application>().resources.getString(R.string.error_empty_search))
    }

    companion object {

        private val TAG = DashboardViewModel::class.java.simpleName
    }

}