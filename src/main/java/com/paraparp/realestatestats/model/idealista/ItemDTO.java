package com.paraparp.realestatestats.model.idealista;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ads",
    "latitude",
    "longitude",
    "type"
})
@Data
public class ItemDTO {

    @JsonProperty("ads")
    public List<AdDTO> ads = new ArrayList<>();
    @JsonProperty("latitude")
    public double latitude;
    @JsonProperty("longitude")
    public double longitude;
    @JsonProperty("type")
    public long type;

}
