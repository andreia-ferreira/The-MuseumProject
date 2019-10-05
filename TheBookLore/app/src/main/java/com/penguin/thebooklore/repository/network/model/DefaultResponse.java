package com.penguin.thebooklore.repository.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DefaultResponse {
    @SerializedName("elapsedMilliseconds")
    @Expose
    private Integer elapsedMilliseconds;
    @SerializedName("count")
    @Expose
    private Integer count;


    public Integer getElapsedMilliseconds() {
        return elapsedMilliseconds;
    }

    public void setElapsedMilliseconds(Integer elapsedMilliseconds) {
        this.elapsedMilliseconds = elapsedMilliseconds;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
