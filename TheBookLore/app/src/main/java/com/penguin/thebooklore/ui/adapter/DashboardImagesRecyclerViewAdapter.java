package com.penguin.thebooklore.ui.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.penguin.thebooklore.R;
import com.penguin.thebooklore.databinding.RecyclerItemDashboardImageBinding;
import com.penguin.thebooklore.model.WebImage;

import java.util.List;

public class DashboardImagesRecyclerViewAdapter extends RecyclerView.Adapter<DashboardImagesRecyclerViewAdapter.DashboardImageViewHolder> {

    private List<WebImage> listImages;
    private Context context;

    public DashboardImagesRecyclerViewAdapter(List<WebImage> listImages, Context context) {
        this.listImages = listImages;
        this.context = context;
    }

    @NonNull
    @Override
    public DashboardImagesRecyclerViewAdapter.DashboardImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerItemDashboardImageBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.recycler_item_dashboard_image, parent, false);

        return new DashboardImageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardImagesRecyclerViewAdapter.DashboardImageViewHolder holder, int position) {
        WebImage image = listImages.get(position);
        holder.bind(image);
    }

    @Override
    public int getItemCount() {
        if (listImages != null) {
            return listImages.size();
        } else {
            return 0;
        }
    }


    public class DashboardImageViewHolder extends RecyclerView.ViewHolder {

        public RecyclerItemDashboardImageBinding binding;

        public DashboardImageViewHolder(RecyclerItemDashboardImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object image) {
            binding.setVariable(BR.image, image);
            binding.executePendingBindings();
        }

    }
}