package com.penguin.thebooklore.repository.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.penguin.thebooklore.model.ArtObject;

import java.util.List;

public class CollectionResponse extends DefaultResponse {

    @SerializedName("artObjects")
    @Expose
    private List<ArtObject> artObjects = null;

    public List<ArtObject> getArtObjects() {
        return artObjects;
    }

    public void setArtObjects(List<ArtObject> artObjects) {
        this.artObjects = artObjects;
    }
}
