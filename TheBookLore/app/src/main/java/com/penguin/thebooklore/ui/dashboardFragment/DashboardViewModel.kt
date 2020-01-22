package com.penguin.thebooklore.ui.dashboardFragment

import android.app.Application
import android.util.Log

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.penguin.thebooklore.R
import com.penguin.thebooklore.model.Artwork

import com.penguin.thebooklore.repository.CollectionRepository
import com.penguin.thebooklore.ui.BaseViewModel
import kotlinx.coroutines.launch

import java.lang.Exception

// injectar repositorio aqui
class DashboardViewModel(application: Application) : BaseViewModel(application) {
    val listArtwork = MutableLiveData<List<Artwork>>()
    private val collectionRepository: CollectionRepository by lazy { CollectionRepository.getInstance(application) }
    var currentPage = 1

    init {
        getCollection("painting")
    }

    fun getCollection(query: String?) {
        isLoading.value = true
        viewModelScope.launch {

            try {
                collectionRepository.getCollection(query ?: "",
                        currentPage,
                        10,
                        { listResponse ->
                            Log.d(TAG, "Success!")
                            if (listResponse.isNotEmpty()) {
                                Log.d(TAG, "Found " + listResponse.size + " results.")
                                listArtwork.value = listResponse
                            } else {
                                isError.value = Exception(getApplication<Application>().resources.getString(R.string.error_empty_search))
                            }
                            isLoading.value = false
                        },
                        {
                            isError.value = Exception(it)
                            isLoading.value = false
                        })
            } catch (e: Exception) {
                isError.value = e
                isLoading.value = false
            }}
    }

    companion object {
        private val TAG = DashboardViewModel::class.java.simpleName
    }

}