package com.penguin.thebooklore.ui.dashboardFragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.penguin.thebooklore.R
import com.penguin.thebooklore.database.MuseumDao
import com.penguin.thebooklore.database.MuseumDatabase
import com.penguin.thebooklore.model.Artwork

import com.penguin.thebooklore.repository.CollectionRepository
import com.penguin.thebooklore.ui.BaseViewModel
import kotlinx.coroutines.launch

import java.lang.Exception

// injectar repositorio aqui
class DashboardViewModel(private val mApplication: Application) : BaseViewModel(mApplication) {
    private val museumDao : MuseumDao by lazy { MuseumDatabase.getDatabase(mApplication).museumDao() }
    private val collectionRepository: CollectionRepository by lazy { CollectionRepository.getInstance(mApplication, museumDao) }
    private val localListArtwork = MutableLiveData<List<Artwork>>()
    val listArtwork :LiveData<List<Artwork>> = localListArtwork
    var currentPage = 1

    init {
        getCollection("painting")
    }

    fun getCollection(query: String?) {
        isLoading.value = true
        viewModelScope.launch {

            try {
                collectionRepository.refreshCollection(
                        query ?: "",
                        currentPage,
                        10,
                        { onSuccessGetCollection(it) },
                        { onErrorNetwork(it) })
            } catch (e: Exception) {
                onErrorNetwork(e.message)
            }}
    }

    private fun onSuccessGetCollection(listResponse: List<Artwork>) {
        if (listResponse.isNotEmpty()) {
            Log.d(TAG, "Found " + listResponse.size + " results.")
            localListArtwork.value = listResponse
            insertArtwork(listResponse)
            isLoading.value = false
            return
        }
        onErrorNetwork(mApplication.resources.getString(R.string.error_empty_search))
    }

    private fun insertArtwork(list: List<Artwork>) {
        viewModelScope.launch {
            try {
                collectionRepository.insertArtwork(list)

            } catch (e: Exception) {
                onError("Error inserting in database")
            }
        }
    }

    private fun getArtworkOffline() {
        viewModelScope.launch {
            try {
                localListArtwork.value = collectionRepository.getArtwork()
            } catch (e: Exception) {
                onError("Error fetching the database")
            }
        }
    }

    private fun onError(error: String?) {
        isError.value = Exception(error)
        isLoading.value = false
    }

    private fun onErrorNetwork(error: String?) {
        getArtworkOffline()
        onError(error)
    }

    companion object {
        private val TAG = DashboardViewModel::class.java.simpleName
    }

}