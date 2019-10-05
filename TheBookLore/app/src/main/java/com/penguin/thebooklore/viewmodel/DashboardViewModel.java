package com.penguin.thebooklore.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText = new MutableLiveData<>();

    public MutableLiveData<String> getText() {
        return mText;
    }

    public DashboardViewModel() {
        mText.setValue("This is dashboard fragment");
    }

}