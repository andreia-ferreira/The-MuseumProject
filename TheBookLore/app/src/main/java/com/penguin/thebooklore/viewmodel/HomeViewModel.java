package com.penguin.thebooklore.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText = new MutableLiveData<>();

    public MutableLiveData<String> getText() {
        return mText;
    }

    public HomeViewModel() {
        mText.setValue("This is home fragment");
    }

}