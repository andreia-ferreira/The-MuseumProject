package com.penguin.thebooklore.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.penguin.thebooklore.R;
import com.penguin.thebooklore.databinding.FragmentDashboardBinding;
import com.penguin.thebooklore.model.WebImage;
import com.penguin.thebooklore.ui.adapter.DashboardImagesRecyclerViewAdapter;
import com.penguin.thebooklore.viewmodel.DashboardViewModel;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private Context context;

    private ArrayList<String> imageList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        context = getContext();
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewmodel(dashboardViewModel);

        initObservers();

        recyclerView = (RecyclerView) binding.getRoot().findViewById(R.id.recycler_collection);
        layoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new DashboardImagesRecyclerViewAdapter(context, imageList);
        recyclerView.setAdapter(mAdapter);

        return binding.getRoot();

    }

    private void initObservers() {
        dashboardViewModel.getListImages().observe(this, list -> {
            imageList.clear();
            imageList.addAll(list);
            mAdapter.notifyDataSetChanged();
        });
    }

}