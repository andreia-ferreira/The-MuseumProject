package com.penguin.thebooklore.repository.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CollectionResponse : DefaultResponse() {

    @SerializedName("artObjects")
    @Expose
    var networkArtObjects: List<NetworkArtObject>? = null
}
