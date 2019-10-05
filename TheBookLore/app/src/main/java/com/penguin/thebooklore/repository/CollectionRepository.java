package com.penguin.thebooklore.repository;

import com.penguin.thebooklore.repository.network.RetrofitHelper;
import com.penguin.thebooklore.repository.network.model.CollectionResponse;

import io.reactivex.Single;

public class CollectionRepository implements ICollectionRepository {

    private static CollectionRepository collectionRepository;

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
