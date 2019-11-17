package com.penguin.thebooklore.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.penguin.thebooklore.R
import com.penguin.thebooklore.databinding.FragmentArtDetailBinding

class ArtDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentArtDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_art_detail, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

}
