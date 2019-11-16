package com.penguin.thebooklore.ui.adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

import com.penguin.thebooklore.R
import com.penguin.thebooklore.databinding.RecyclerItemDashboardImageBinding
import com.penguin.thebooklore.model.WebImage

class DashboardImagesRecyclerViewAdapter(private val context: Context, private val listImages: List<String>?) : RecyclerView.Adapter<DashboardImagesRecyclerViewAdapter.DashboardImageViewHolder>() {

    lateinit var binding: RecyclerItemDashboardImageBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardImageViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.recycler_item_dashboard_image,
                parent,
                false)

        return DashboardImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashboardImageViewHolder, position: Int) {
        val imageUrl = listImages!![position]
        holder.bind(imageUrl)
    }

    override fun getItemCount(): Int {
        return listImages?.size ?: 0
    }


    inner class DashboardImageViewHolder(var binding: RecyclerItemDashboardImageBinding) : RecyclerView.ViewHolder(binding.getRoot()) {

        fun bind(imageUrl: Any) {
            binding.setVariable(BR.imageUrl, imageUrl)
            binding.executePendingBindings()
        }

    }
}
