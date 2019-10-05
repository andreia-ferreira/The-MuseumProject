package com.penguin.thebooklore.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.penguin.thebooklore.repository.CollectionRepository;
import com.penguin.thebooklore.repository.network.model.CollectionResponse;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DashboardViewModel extends ViewModel {

    private static final String TAG = DashboardViewModel.class.getSimpleName();

    private MutableLiveData<String> mText = new MutableLiveData<>();
    private CollectionRepository repository;

    public MutableLiveData<String> getText() {
        return mText;
    }

    public DashboardViewModel() {
        repository = CollectionRepository.getInstance();

        mText.setValue("This is dashboard fragment");
        getCollection();
    }

    public void getCollection() {

        repository.getCollection()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CollectionResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "Subscribed");
                    }

                    @Override
                    public void onSuccess(CollectionResponse collectionResponse) {
                        Log.d(TAG, "Completed!");
                        Log.d(TAG, collectionResponse.getArtObjects().get(0).getWebImage().getUrl());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.getMessage());
                    }
                });


    }

}