package com.penguin.thebooklore.repository.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.penguin.thebooklore.model.ArtObject

class CollectionResponse : DefaultResponse() {

    @SerializedName("artObjects")
    @Expose
    var artObjects: List<ArtObject>? = null
}
