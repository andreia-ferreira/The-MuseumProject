package com.penguin.thebooklore.ui.dashboardFragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.penguin.thebooklore.database.MuseumDao
import com.penguin.thebooklore.database.MuseumDatabase
import com.penguin.thebooklore.model.Artwork
import com.penguin.thebooklore.network.RetrofitHelper

import com.penguin.thebooklore.repository.CollectionRepository
import com.penguin.thebooklore.ui.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

import java.lang.Exception

// injectar repositorio aqui
class DashboardViewModel(private val mApplication: Application) : BaseViewModel(mApplication) {
    private val museumDao : MuseumDao by lazy { MuseumDatabase.getDatabase(mApplication).museumDao() }
    private val collectionRepository: CollectionRepository by lazy { CollectionRepository.getInstance(mApplication, museumDao, RetrofitHelper.getInstance(mApplication)) }
    val listArtwork :LiveData<List<Artwork>> = collectionRepository.artwork
    var currentPage = 1
    val searchText by lazy { MutableLiveData("painting") }
    val isError = collectionRepository.reposErrors

    init {
        refreshCollection(searchText.value)
        Log.d(TAG, "init")
    }

    fun refreshCollection(query: String?) {
        showLoading()
        searchText.value = query ?: searchText.value

        viewModelScope.launch {
            val completed = async { collectionRepository.refreshCollection(query ?: "", currentPage, 10) }

            if (completed.await()) {
                collectionRepository.getArtworkFromDatabase(query ?: "")
            }
        }
    }

    companion object {
        private val TAG = DashboardViewModel::class.java.simpleName
    }

}