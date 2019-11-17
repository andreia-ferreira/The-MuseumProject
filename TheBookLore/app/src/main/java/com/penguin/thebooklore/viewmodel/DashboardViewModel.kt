package com.penguin.thebooklore.viewmodel

import android.util.Log

import androidx.lifecycle.MutableLiveData

import com.penguin.thebooklore.model.ArtObject
import com.penguin.thebooklore.repository.CollectionRepository
import com.penguin.thebooklore.repository.network.model.CollectionResponse

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class DashboardViewModel : BaseViewModel() {
    val listArtObjects = MutableLiveData<List<ArtObject>>()
    private val collectionRepository: CollectionRepository = CollectionRepository

    init {
        getCollection()
    }

    private fun getCollection() {
        val disposable = collectionRepository.getCollection("painting", 10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onSuccess(it)}, { onError(it)})
        addDisposable(disposable)
    }

    private fun onSuccess(response: CollectionResponse) {
        Log.d(TAG, "Success!")
        response.artObjects?.let {artObjects ->

            if (artObjects.isNotEmpty()) {
                Log.d(TAG, "Found " + artObjects.size + " results.")
                listArtObjects.value = artObjects
                return
            }

        }
        isError.value = Exception("No art objects were found")
    }

    private fun onError(e: Throwable) {
        isError.value = Exception("There was an error while fetching the results.\n Please try again later")
        e.printStackTrace()
        e.message?.let {
            Log.d(TAG, it)
        }
    }

    companion object {

        private val TAG = DashboardViewModel::class.java.simpleName
    }

}