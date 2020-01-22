package com.penguin.thebooklore.ui.notificationsFragment

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.penguin.thebooklore.ui.BaseViewModel

class NotificationsViewModel(application: Application) : BaseViewModel(application) {

    val text = MutableLiveData<String>()

    init {
        text.value = "This is profile fragment"
    }

}