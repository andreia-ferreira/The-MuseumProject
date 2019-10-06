package com.penguin.thebooklore.repository;

import com.penguin.thebooklore.model.ArtObject;
import com.penguin.thebooklore.repository.network.model.CollectionResponse;

import java.util.List;

import io.reactivex.Single;

public interface ICollectionRepository {
    Single<CollectionResponse> getCollection();
}
