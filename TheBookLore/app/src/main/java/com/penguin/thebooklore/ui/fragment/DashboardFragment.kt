package com.penguin.thebooklore.ui.fragment

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
import com.penguin.thebooklore.model.ArtObject
import com.penguin.thebooklore.model.networkModel.NetworkArtObject
import com.penguin.thebooklore.ui.adapter.DashboardImagesRecyclerViewAdapter
import com.penguin.thebooklore.viewmodel.DashboardViewModel
import java.util.*

class DashboardFragment : Fragment() {

    private val dashboardViewModel: DashboardViewModel by lazy { ViewModelProviders.of(this).get(DashboardViewModel::class.java) }
    private lateinit var binding: FragmentDashboardBinding
    private val artObjectsList = ArrayList<ArtObject>()
    private val layoutManager by lazy { GridLayoutManager(context, 2) }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = dashboardViewModel
        binding.layoutManager = layoutManager
        binding.adapter = DashboardImagesRecyclerViewAdapter(context!!, artObjectsList) { artObject: ArtObject ->  onClickArtObject(artObject)}

        initObservers()

        return binding.root

    }

    private fun onClickArtObject(artObject: ArtObject) {
        Toast.makeText(context, "Open Sesame, ${artObject.title}", Toast.LENGTH_LONG).show()
    }

    private fun initObservers() {
        dashboardViewModel.listArtObjects.observe(this, Observer { list ->
            artObjectsList.clear()
            artObjectsList.addAll(list)
            binding.adapter!!.notifyDataSetChanged()
        })

        dashboardViewModel.isError.observe(this, Observer {exception ->
            exception?.message?.run{
                Toast.makeText(context, this, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        if (dashboardViewModel.listArtObjects.hasObservers()) dashboardViewModel.listArtObjects.removeObservers(viewLifecycleOwner)
        if (dashboardViewModel.isError.hasObservers()) dashboardViewModel.isError.removeObservers(viewLifecycleOwner)
    }

}