package com.penguin.thebooklore.ui.dashboardFragment

import android.app.Application
import android.util.Log

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.penguin.thebooklore.R
import com.penguin.thebooklore.model.Artwork

import com.penguin.thebooklore.repository.CollectionRepository
import com.penguin.thebooklore.model.networkModel.Result
import com.penguin.thebooklore.ui.BaseViewModel
import kotlinx.coroutines.launch

import java.lang.Exception

// injectar repositorio aqui
class DashboardViewModel(application: Application) : BaseViewModel(application) {
    val listArtwork = MutableLiveData<List<Artwork>>()
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
                // replace with generic error one day
                isError.value = e
            }
            isLoading.value = false
        }
    }

    private fun processDataResponse(response: List<Artwork>) {
        Log.d(TAG, "Success!")
        if (response.isNotEmpty()) {
            Log.d(TAG, "Found " + response.size + " results.")
            listArtwork.value = response
            currentPage ++
            return
        }
        isError.value = Exception(getApplication<Application>().resources.getString(R.string.error_empty_search))
    }

    companion object {
        private val TAG = DashboardViewModel::class.java.simpleName
    }

}