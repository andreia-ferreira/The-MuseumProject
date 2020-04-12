package com.penguin.thebooklore.ui.dashboardFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.penguin.thebooklore.database.Cache
import com.penguin.thebooklore.database.MuseumDatabase
import com.penguin.thebooklore.model.Artwork
import com.penguin.thebooklore.network.RetrofitHelper

import com.penguin.thebooklore.repository.CollectionRepository
import com.penguin.thebooklore.ui.BaseViewModel

import java.util.concurrent.Executors

// injectar repositorio aqui
class DashboardViewModel(private val mApplication: Application) : AndroidViewModel(mApplication) {
    private val cache : Cache by lazy { Cache(MuseumDatabase.getDatabase(mApplication).museumDao(), Executors.newSingleThreadExecutor()) }
    private val collectionRepository: CollectionRepository by lazy { CollectionRepository.getInstance(mApplication, cache, RetrofitHelper.getInstance(mApplication)) }
    private val searchText by lazy { MutableLiveData("painting") }
    val listArtwork :LiveData<List<Artwork>> = Transformations.switchMap(searchText) { collectionRepository.search(it ?: "") }
    val isError = collectionRepository.reposErrors
    val isLoading = collectionRepository.isRequestInProgress

    init {
        refreshCollection(searchText.value)
    }

    fun refreshCollection(query: String?) {
        searchText.postValue(query)
    }

    companion object {
        private val TAG = DashboardViewModel::class.java.simpleName
    }

}