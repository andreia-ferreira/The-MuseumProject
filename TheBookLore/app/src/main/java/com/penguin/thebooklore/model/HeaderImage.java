package com.penguin.thebooklore.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeaderImage {
    @SerializedName("guid")
    @Expose
    private String guid;
    @SerializedName("offsetPercentageX")
    @Expose
    private Integer offsetPercentageX;
    @SerializedName("offsetPercentageY")
    @Expose
    private Integer offsetPercentageY;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("url")
    @Expose
    private String url;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Integer getOffsetPercentageX() {
        return offsetPercentageX;
    }

    public void setOffsetPercentageX(Integer offsetPercentageX) {
        this.offsetPercentageX = offsetPercentageX;
    }

    public Integer getOffsetPercentageY() {
        return offsetPercentageY;
    }

    public void setOffsetPercentageY(Integer offsetPercentageY) {
        this.offsetPercentageY = offsetPercentageY;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
