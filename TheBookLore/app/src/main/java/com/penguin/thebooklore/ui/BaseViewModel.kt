package com.penguin.thebooklore.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val isLoading = MutableLiveData<Boolean>()

    init {
        isLoading.value = false
    }

    fun showLoading() {
        isLoading.value = true
    }

    fun hideLoading() {
        isLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()
    }

}