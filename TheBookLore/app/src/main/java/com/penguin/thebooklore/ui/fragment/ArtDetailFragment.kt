package com.penguin.thebooklore.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.penguin.thebooklore.R
import com.penguin.thebooklore.databinding.FragmentArtDetailBinding
import com.penguin.thebooklore.viewmodel.ArtDetailViewModel

class ArtDetailFragment : Fragment() {

    private lateinit var binding: FragmentArtDetailBinding
    private val detailViewModel: ArtDetailViewModel by lazy { ViewModelProviders.of(this).get(ArtDetailViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_art_detail, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = detailViewModel

        return binding.root
    }

}
