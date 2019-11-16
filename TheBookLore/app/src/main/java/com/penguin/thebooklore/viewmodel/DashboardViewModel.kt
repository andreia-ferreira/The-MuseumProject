package com.penguin.thebooklore.viewmodel

import android.icu.util.LocaleData
import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.penguin.thebooklore.model.ArtObject
import com.penguin.thebooklore.model.WebImage
import com.penguin.thebooklore.repository.CollectionRepository
import com.penguin.thebooklore.repository.network.model.CollectionResponse

import java.util.ArrayList

import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DashboardViewModel : ViewModel() {
    val listArtObjects = MutableLiveData<List<ArtObject>>()
    val listImages = MutableLiveData<List<String>>()

    init {
        getCollection()
    }

    private fun getCollection() {
        CollectionRepository.collection
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<CollectionResponse> {
                    override fun onSubscribe(d: Disposable) {
                        Log.d(TAG, "Subscribed! Need to treat disposables.")
                    }

                    override fun onSuccess(collectionResponse: CollectionResponse) {
                        Log.d(TAG, "Success!")
                        collectionResponse.artObjects?.let {artObjects ->

                        if (artObjects.isNotEmpty()) {
                            Log.d(TAG, "Found " + artObjects.size + " results.")
                            listArtObjects.value = artObjects

                            listImages.value = artObjects.map { item ->
                                item.webImage?.url.let { image ->
                                    image.toString()
                                }
                            }

                        }

                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "SHITE")
                        e.message?.let { message ->
                            Log.d(TAG, message)
                        }
                    }
                })


    }

    companion object {

        private val TAG = DashboardViewModel::class.java.simpleName
    }

}