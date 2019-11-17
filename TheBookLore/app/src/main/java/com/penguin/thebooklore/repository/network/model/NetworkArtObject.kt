package com.penguin.thebooklore.repository.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NetworkArtObject {
    @SerializedName("links")
    @Expose
    var links: NetworkHeaderImage? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("objectNumber")
    @Expose
    var objectNumber: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("hasImage")
    @Expose
    var hasImage: Boolean? = null
    @SerializedName("principalOrFirstMaker")
    @Expose
    var principalOrFirstMaker: String? = null
    @SerializedName("longTitle")
    @Expose
    var longTitle: String? = null
    @SerializedName("showImage")
    @Expose
    var showImage: Boolean? = null
    @SerializedName("permitDownload")
    @Expose
    var permitDownload: Boolean? = null
    @SerializedName("webImage")
    @Expose
    var networkWebImage: NetworkWebImage? = null
    @SerializedName("headerImage")
    @Expose
    var networkHeaderImage: NetworkHeaderImage? = null
    @SerializedName("productionPlaces")
    @Expose
    var productionPlaces: List<String>? = null

}
