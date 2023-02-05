package com.paraparp.realestatestats.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ad {

    @JsonProperty("adId")
    public long adId;
    @JsonProperty("isFavourited")
    public boolean isFavourited;
    @JsonProperty("price")
    public long price;
    @JsonProperty("priceText")
    public String priceText;
    @JsonProperty("isAuction")
    public boolean isAuction;

}
