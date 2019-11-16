package com.penguin.thebooklore.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.penguin.thebooklore.R
import com.penguin.thebooklore.databinding.FragmentDashboardBinding
import com.penguin.thebooklore.model.ArtObject
import com.penguin.thebooklore.ui.adapter.DashboardImagesRecyclerViewAdapter
import com.penguin.thebooklore.viewmodel.DashboardViewModel
import java.util.*

class DashboardFragment : Fragment() {

    private val dashboardViewModel: DashboardViewModel by lazy { ViewModelProviders.of(this).get(DashboardViewModel::class.java) }
    private lateinit var binding: FragmentDashboardBinding

    private val artObjectsList = ArrayList<ArtObject>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var mAdapter: RecyclerView.Adapter<DashboardImagesRecyclerViewAdapter.DashboardImageViewHolder>
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = dashboardViewModel

        initObservers()

        recyclerView =  binding.root.findViewById(R.id.recycler_collection) as RecyclerView
        mAdapter = DashboardImagesRecyclerViewAdapter(context!!, artObjectsList)
        layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter

        return binding.root

    }

    private fun initObservers() {
        dashboardViewModel.listArtObjects.observe(this, Observer { list ->
            artObjectsList.clear()
            artObjectsList.addAll(list)
            mAdapter.notifyDataSetChanged()
        })
    }

}