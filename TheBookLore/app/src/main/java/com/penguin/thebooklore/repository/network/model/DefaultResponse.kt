package com.penguin.thebooklore.repository.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class DefaultResponse {
    @SerializedName("elapsedMilliseconds")
    @Expose
    var elapsedMilliseconds: Int? = null
    @SerializedName("count")
    @Expose
    var count: Int? = null

}
