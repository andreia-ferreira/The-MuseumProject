package com.penguin.thebooklore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    val text = MutableLiveData<String>()

    init {
        text.value = "This is home fragment"
    }

}