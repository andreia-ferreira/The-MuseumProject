package com.penguin.thebooklore.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.Exception

open class BaseViewModel: ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val isError = MutableLiveData<Exception>()

    init {
        isError.value = null
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}