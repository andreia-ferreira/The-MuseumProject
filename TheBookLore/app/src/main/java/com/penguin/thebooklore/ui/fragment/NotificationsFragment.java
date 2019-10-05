package com.penguin.thebooklore.ui.fragment;

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

import com.penguin.thebooklore.R;
import com.penguin.thebooklore.databinding.FragmentNotificationsBinding;
import com.penguin.thebooklore.viewmodel.NotificationsViewModel;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notifications, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewmodel(notificationsViewModel);

        return binding.getRoot();
    }
}