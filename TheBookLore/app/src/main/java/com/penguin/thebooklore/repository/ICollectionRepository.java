package com.penguin.thebooklore.repository;

import com.penguin.thebooklore.repository.network.model.CollectionResponse;

import io.reactivex.Single;

public interface ICollectionRepository {
    Single<CollectionResponse> getCollection();
}
