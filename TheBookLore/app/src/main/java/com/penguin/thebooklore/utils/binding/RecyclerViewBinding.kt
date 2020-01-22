package com.penguin.thebooklore.utils.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("layoutManager")
    fun setLayoutManager(recyclerView: RecyclerView, layoutManager: RecyclerView.LayoutManager) {
        recyclerView.layoutManager = layoutManager
    }

    @JvmStatic
    @BindingAdapter("adapter")
    fun setAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerView.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("scrollListener")
    fun setScrollListener(recyclerView: RecyclerView, scrollListener: RecyclerView.OnScrollListener) {
        recyclerView.addOnScrollListener(scrollListener)
    }

}
