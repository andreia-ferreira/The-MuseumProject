package com.penguin.thebooklore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val isError = MutableLiveData<Exception>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        isError.value = null
        isLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()
    }

}