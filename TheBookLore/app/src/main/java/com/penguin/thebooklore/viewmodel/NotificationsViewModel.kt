package com.penguin.thebooklore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    val text = MutableLiveData<String>()

    init {
        text.value = "This is notifications fragment"
    }

}