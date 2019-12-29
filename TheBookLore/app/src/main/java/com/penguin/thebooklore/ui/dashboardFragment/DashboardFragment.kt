package com.penguin.thebooklore.ui.dashboardFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.penguin.thebooklore.R
import com.penguin.thebooklore.databinding.FragmentDashboardBinding
import com.penguin.thebooklore.model.Artwork
import com.penguin.thebooklore.ui.adapter.DashboardImagesRecyclerViewAdapter
import java.util.*

class DashboardFragment : Fragment() {

    private lateinit var mContext: Context
    private lateinit var binding: FragmentDashboardBinding
    private val dashboardViewModel: DashboardViewModel by lazy { ViewModelProviders.of(this).get(DashboardViewModel::class.java) }
    private val layoutManager by lazy { GridLayoutManager(mContext, 2) }
    private val artworkList = ArrayList<Artwork>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = dashboardViewModel
        binding.layoutManager = layoutManager
        binding.adapter = DashboardImagesRecyclerViewAdapter(mContext, artworkList) { artwork: Artwork ->  onClickArtwork(artwork)}

        initObservers()

        return binding.root

    }

    private fun onClickArtwork(artwork: Artwork) {
        Toast.makeText(mContext, "Open Sesame, ${artwork.title}", Toast.LENGTH_LONG).show()
    }

    private fun initObservers() {
        dashboardViewModel.listArtwork.observe(this, Observer { list ->
            artworkList.clear()
            artworkList.addAll(list)
            binding.adapter!!.notifyDataSetChanged()
        })

        dashboardViewModel.isError.observe(this, Observer {exception ->
            exception?.message?.run{
                Toast.makeText(mContext, this, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        if (dashboardViewModel.listArtwork.hasObservers()) dashboardViewModel.listArtwork.removeObservers(viewLifecycleOwner)
        if (dashboardViewModel.isError.hasObservers()) dashboardViewModel.isError.removeObservers(viewLifecycleOwner)
    }

}