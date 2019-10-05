package com.penguin.thebooklore.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtObject {
    @SerializedName("links")
    @Expose
    private HeaderImage links;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("objectNumber")
    @Expose
    private String objectNumber;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("hasImage")
    @Expose
    private Boolean hasImage;
    @SerializedName("principalOrFirstMaker")
    @Expose
    private String principalOrFirstMaker;
    @SerializedName("longTitle")
    @Expose
    private String longTitle;
    @SerializedName("showImage")
    @Expose
    private Boolean showImage;
    @SerializedName("permitDownload")
    @Expose
    private Boolean permitDownload;
    @SerializedName("webImage")
    @Expose
    private WebImage webImage;
    @SerializedName("headerImage")
    @Expose
    private HeaderImage headerImage;
    @SerializedName("productionPlaces")
    @Expose
    private List<String> productionPlaces = null;

    public HeaderImage getLinks() {
        return links;
    }

    public void setLinks(HeaderImage links) {
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjectNumber() {
        return objectNumber;
    }

    public void setObjectNumber(String objectNumber) {
        this.objectNumber = objectNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getHasImage() {
        return hasImage;
    }

    public void setHasImage(Boolean hasImage) {
        this.hasImage = hasImage;
    }

    public String getPrincipalOrFirstMaker() {
        return principalOrFirstMaker;
    }

    public void setPrincipalOrFirstMaker(String principalOrFirstMaker) {
        this.principalOrFirstMaker = principalOrFirstMaker;
    }

    public String getLongTitle() {
        return longTitle;
    }

    public void setLongTitle(String longTitle) {
        this.longTitle = longTitle;
    }

    public Boolean getShowImage() {
        return showImage;
    }

    public void setShowImage(Boolean showImage) {
        this.showImage = showImage;
    }

    public Boolean getPermitDownload() {
        return permitDownload;
    }

    public void setPermitDownload(Boolean permitDownload) {
        this.permitDownload = permitDownload;
    }

    public WebImage getWebImage() {
        return webImage;
    }

    public void setWebImage(WebImage webImage) {
        this.webImage = webImage;
    }

    public HeaderImage getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(HeaderImage headerImage) {
        this.headerImage = headerImage;
    }

    public List<String> getProductionPlaces() {
        return productionPlaces;
    }

    public void setProductionPlaces(List<String> productionPlaces) {
        this.productionPlaces = productionPlaces;
    }

}
