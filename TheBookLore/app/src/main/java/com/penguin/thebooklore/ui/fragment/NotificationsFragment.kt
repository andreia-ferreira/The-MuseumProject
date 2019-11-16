package com.penguin.thebooklore.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.penguin.thebooklore.R
import com.penguin.thebooklore.databinding.FragmentNotificationsBinding
import com.penguin.thebooklore.viewmodel.NotificationsViewModel

class NotificationsFragment : Fragment() {

    private val notificationsViewModel: NotificationsViewModel by lazy { ViewModelProviders.of(this).get(NotificationsViewModel::class.java) }
    private lateinit var binding: FragmentNotificationsBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notifications, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = notificationsViewModel

        return binding.root
    }
}