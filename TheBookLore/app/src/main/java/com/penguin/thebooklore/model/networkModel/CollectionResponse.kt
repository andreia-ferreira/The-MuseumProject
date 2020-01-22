package com.penguin.thebooklore.model.networkModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CollectionResponse : DefaultResponse() {

    @SerializedName("artObjects")
    @Expose
    var networkArtworks: List<NetworkArtwork>? = null
}
