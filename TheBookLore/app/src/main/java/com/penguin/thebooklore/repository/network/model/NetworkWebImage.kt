package com.penguin.thebooklore.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WebImage {
    @SerializedName("guid")
    @Expose
    var guid: String? = null
    @SerializedName("offsetPercentageX")
    @Expose
    var offsetPercentageX: Int? = null
    @SerializedName("offsetPercentageY")
    @Expose
    var offsetPercentageY: Int? = null
    @SerializedName("width")
    @Expose
    var width: Int? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
}
