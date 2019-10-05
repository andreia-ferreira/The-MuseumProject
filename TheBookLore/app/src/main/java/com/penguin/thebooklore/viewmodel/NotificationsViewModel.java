package com.penguin.thebooklore.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText = new MutableLiveData<>();

    public MutableLiveData<String> getText() {
        return mText;
    }

    public NotificationsViewModel() {
        mText.setValue("This is notifications fragment");
    }

}