package com.penguin.thebooklore.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArtObject {
    @SerializedName("links")
    @Expose
    var links: HeaderImage? = null
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
    var webImage: WebImage? = null
    @SerializedName("headerImage")
    @Expose
    var headerImage: HeaderImage? = null
    @SerializedName("productionPlaces")
    @Expose
    var productionPlaces: List<String>? = null

}
