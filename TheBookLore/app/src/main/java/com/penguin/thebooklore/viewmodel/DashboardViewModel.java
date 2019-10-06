package com.penguin.thebooklore.viewmodel;

import android.icu.util.LocaleData;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.penguin.thebooklore.model.ArtObject;
import com.penguin.thebooklore.model.WebImage;
import com.penguin.thebooklore.repository.CollectionRepository;
import com.penguin.thebooklore.repository.network.model.CollectionResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DashboardViewModel extends ViewModel {

    private static final String TAG = DashboardViewModel.class.getSimpleName();
    private CollectionRepository repository;
    private MutableLiveData<List<ArtObject>> listArtObjects = new MutableLiveData<>();
    private MutableLiveData<List<String>> listImages = new MutableLiveData<>();

    public MutableLiveData<List<ArtObject>> getListArtObjects() {
        return listArtObjects;
    }
    public MutableLiveData<List<String>> getListImages() {
        return listImages;
    }

    public DashboardViewModel() {
        repository = CollectionRepository.getInstance();

        getCollection();
    }

    private void getCollection() {
        final ArrayList<ArtObject> artObjectArrayList = new ArrayList<>();
        ArrayList<String> webImages = new ArrayList<>();

        repository.getCollection()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CollectionResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "Subscribed! Need to treat disposables.");
                    }

                    @Override
                    public void onSuccess(CollectionResponse collectionResponse) {
                        listArtObjects.setValue(collectionResponse.getArtObjects());
                        Log.d(TAG, "Success!");


                        for (ArtObject artObject : listArtObjects.getValue()) {
                            webImages.add(artObject.getWebImage().getUrl());
                        }

//        listArtObjects.setValue(artObjectArrayList);
                        listImages.setValue(webImages);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "SHITE");
                        Log.d(TAG, e.getMessage());
                    }
                });



    }

}