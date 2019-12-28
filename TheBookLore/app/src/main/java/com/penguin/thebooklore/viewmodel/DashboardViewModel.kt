package com.penguin.thebooklore.viewmodel

import android.app.Application
import android.util.Log

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.penguin.thebooklore.R
import com.penguin.thebooklore.model.ArtObject

import com.penguin.thebooklore.repository.CollectionRepository
import com.penguin.thebooklore.model.networkModel.Result
import kotlinx.coroutines.launch

import java.lang.Exception

// injectar repositorio aqui
class DashboardViewModel(application: Application) : BaseViewModel(application) {
    val listArtObjects = MutableLiveData<List<ArtObject>>()
    private val collectionRepository: CollectionRepository by lazy { CollectionRepository.getInstance(application) }
    var currentPage = 1

    init {
        getCollection()
    }

    private fun getCollection() {
        isLoading.value = true
        viewModelScope.launch {
            try {
                when (val collectionResponse = collectionRepository.getCollection("painting", 10, currentPage)) {
                    is Result.Success -> processDataResponse(collectionResponse.value)
                    is Result.Failure -> throw Exception(collectionResponse.throwable)
                }

            } catch (e: Exception) {
                isError.value = e
            }
            isLoading.value = false
        }
    }

    private fun processDataResponse(response: List<ArtObject>) {
        Log.d(TAG, "Success!")
        if (response.isNotEmpty()) {
            Log.d(TAG, "Found " + response.size + " results.")
            listArtObjects.value = response
            currentPage ++
            return
        }
        isError.value = Exception(getApplication<Application>().resources.getString(R.string.error_empty_search))
    }

    companion object {
        private val TAG = DashboardViewModel::class.java.simpleName
    }

}