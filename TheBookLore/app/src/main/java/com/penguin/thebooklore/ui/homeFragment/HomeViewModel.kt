package com.penguin.thebooklore.ui.homeFragment

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.penguin.thebooklore.ui.BaseViewModel

class HomeViewModel(application: Application) : BaseViewModel(application) {

    val text = MutableLiveData<String>()

    init {
        text.value = "This is home fragment"
    }

}