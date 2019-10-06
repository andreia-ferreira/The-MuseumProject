package com.penguin.thebooklore.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.penguin.thebooklore.model.ArtObject;
import com.penguin.thebooklore.repository.network.RetrofitHelper;
import com.penguin.thebooklore.repository.network.model.CollectionResponse;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CollectionRepository implements ICollectionRepository {

    private static final String TAG = CollectionRepository.class.getSimpleName();
    private static CollectionRepository collectionRepository;
    private MutableLiveData<List<ArtObject>> listArtObjects = new MutableLiveData<>();

    private CollectionRepository() {}

    public static CollectionRepository getInstance() {
        if (collectionRepository == null) {
            collectionRepository = new CollectionRepository();
        }

        return collectionRepository;

    }


    @Override
    public Single<CollectionResponse> getCollection() {
        return RetrofitHelper.getInstance().getCollection();
    }

}
