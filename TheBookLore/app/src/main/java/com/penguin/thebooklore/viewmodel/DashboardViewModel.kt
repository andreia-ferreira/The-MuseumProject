package com.penguin.thebooklore.viewmodel

import android.app.Application
import android.util.Log

import androidx.lifecycle.MutableLiveData
import com.penguin.thebooklore.R

import com.penguin.thebooklore.repository.network.model.NetworkArtObject
import com.penguin.thebooklore.repository.CollectionRepository
import com.penguin.thebooklore.repository.network.model.CollectionResponse

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
        val disposable = collectionRepository.getCollection("painting", 10, currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onSuccess(it)}, { onError(it)})

        addDisposable(disposable)
    }

    private fun onSuccess(response: CollectionResponse) {
        Log.d(TAG, "Success!")
        response.networkArtObjects?.let { artObjects ->

            if (artObjects.isNotEmpty()) {
                Log.d(TAG, "Found " + artObjects.size + " results.")
                listArtObjects.value = artObjects
                currentPage ++
                isLoading.value = false
                return
            }

        }
        isError.value = Exception(getApplication<Application>().resources.getString(R.string.error_empty_search))
        isLoading.value = false
    }

    private fun onError(e: Throwable) {
        isError.value = Exception(getApplication<Application>().resources.getString(R.string.error_service_call))
        e.printStackTrace()
        e.message?.let {
            Log.d(TAG, it)
        }
        isLoading.value = false
    }

    companion object {

        private val TAG = DashboardViewModel::class.java.simpleName
    }

}