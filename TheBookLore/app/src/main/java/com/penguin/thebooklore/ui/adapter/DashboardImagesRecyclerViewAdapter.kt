package com.penguin.thebooklore.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

import com.penguin.thebooklore.R
import com.penguin.thebooklore.databinding.RecyclerItemDashboardImageBinding
import com.penguin.thebooklore.model.ArtObject

class DashboardImagesRecyclerViewAdapter(private val context: Context, private val listArtObject: List<ArtObject>, private val clickListenerOpenArtDetail: (ArtObject) -> Unit):
        RecyclerView.Adapter<DashboardImagesRecyclerViewAdapter.DashboardImageViewHolder>() {

    lateinit var binding: RecyclerItemDashboardImageBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardImageViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.recycler_item_dashboard_image,
                parent,
                false)

        return DashboardImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashboardImageViewHolder, position: Int) {
        holder.bind(listArtObject[position], clickListenerOpenArtDetail)
    }

    override fun getItemCount(): Int {
        return listArtObject.size
    }


    inner class DashboardImageViewHolder(var binding: RecyclerItemDashboardImageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(artObject: ArtObject, clickListener: (ArtObject) -> Unit) {
            binding.setVariable(BR.artObject, artObject)
            binding.setOnClickListenerOpenDetailArtObject { clickListener(artObject) }
            binding.executePendingBindings()
        }

    }
}
