package com.penguin.thebooklore.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel(application: Application) : BaseViewModel(application) {

    val text = MutableLiveData<String>()

    init {
        text.value = "This is notifications fragment"
    }

}