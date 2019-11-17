package com.penguin.thebooklore.ui.fragment

import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {

    fun doOnError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}