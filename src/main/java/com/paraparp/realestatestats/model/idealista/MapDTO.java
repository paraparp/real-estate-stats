package com.paraparp.realestatestats.model.idealista;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MapDTO {

    @JsonProperty("centreLat")
    public double centreLat;
    @JsonProperty("centreLong")
    public double centreLong;
    @JsonProperty("height")
    public long height;
    @JsonProperty("width")
    public long width;
    @JsonProperty("northEastLat")
    public double northEastLat;
    @JsonProperty("northEastLong")
    public double northEastLong;
    @JsonProperty("southWestLat")
    public double southWestLat;
    @JsonProperty("southWestLong")
    public double southWestLong;
    @JsonProperty("zoomLevel")
    public long zoomLevel;
    @JsonProperty("items")
    public List<ItemDTO> items = new ArrayList<>();

}
