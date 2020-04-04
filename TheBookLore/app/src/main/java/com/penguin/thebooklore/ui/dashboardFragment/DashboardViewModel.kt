package com.penguin.thebooklore.ui.dashboardFragment

import android.app.Application
import android.util.Log

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
    val listArtwork = MutableLiveData<List<Artwork>>()
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
                        { onError(it) })
            } catch (e: Exception) {
                onError(e.message)
            }}
    }

    fun insertArtwork(list: List<Artwork>) {
        viewModelScope.launch {
            try {
                collectionRepository.insertArtwork(list)
            } catch (e: Exception) {
                onError("Error inserting in database")
            }
        }
    }

    private fun onSuccessGetCollection(listResponse: List<Artwork>) {
        if (listResponse.isNotEmpty()) {
            Log.d(TAG, "Found " + listResponse.size + " results.")
            listArtwork.value = listResponse
            isLoading.value = false
            insertArtwork(listResponse)
            return
        }
        onError(mApplication.resources.getString(R.string.error_empty_search))
    }

    private fun onError(error: String?) {
        isError.value = Exception(error)
        isLoading.value = false
    }

    companion object {
        private val TAG = DashboardViewModel::class.java.simpleName
    }

}