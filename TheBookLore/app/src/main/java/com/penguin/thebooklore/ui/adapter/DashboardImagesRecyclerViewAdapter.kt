package com.penguin.thebooklore.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

import com.penguin.thebooklore.R
import com.penguin.thebooklore.databinding.RecyclerItemDashboardImageBinding
import com.penguin.thebooklore.model.Artwork

class DashboardImagesRecyclerViewAdapter(private val context: Context, private val listArtwork: List<Artwork>, private val clickListenerOpenArtDetail: (Artwork) -> Unit):
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
        holder.bind(listArtwork[position], clickListenerOpenArtDetail)
    }

    override fun getItemCount(): Int {
        return listArtwork.size
    }


    inner class DashboardImageViewHolder(var binding: RecyclerItemDashboardImageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(artwork: Artwork, clickListener: (Artwork) -> Unit) {
            binding.setVariable(BR.artwork, artwork)
            binding.setOnClickListenerOpenDetailArtwork { clickListener(artwork) }
            binding.executePendingBindings()
        }

    }
}
