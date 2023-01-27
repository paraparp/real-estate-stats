package com.paraparp.realstatestats.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {

    @JsonProperty("valueH1")
    public String valueH1;
    @JsonProperty("description")
    public String description;
    @JsonProperty("search")
    public String search;
    @JsonProperty("breadcrumbUrlValue")
    public String breadcrumbUrlValue;
    @JsonProperty("mapSearchUrl")
    public String mapSearchUrl;
    @JsonProperty("listingSearchUrl")
    public String listingSearchUrl;
    @JsonProperty("pagetarget")
    public String pagetarget;
    @JsonProperty("searchTotalsUrl")
    public String searchTotalsUrl;
    @JsonProperty("searchWithoutFilters")
    public boolean searchWithoutFilters;
    @JsonProperty("listingTotalResults")
    public String listingTotalResults;
    @JsonProperty("listingPriceByArea")
    public String listingPriceByArea;
    @JsonProperty("howmany")
    public String howmany;
    @JsonProperty("existAlert")
    public boolean existAlert;
    @JsonProperty("map")
    public Map map;

}
