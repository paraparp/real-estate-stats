package com.paraparp.realestatestats.model.idealista;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@lombok.Data
public class MainObjectIdealistaDTO {

    @JsonProperty("result")
    public String result;
    @JsonProperty("message")
    public Object message;
    @JsonProperty("code")
    public String code;
    @JsonProperty("fieldErrors")
    public List<Object> fieldErrors = null;
    @JsonProperty("globalErrors")
    public List<Object> globalErrors = null;
    @JsonProperty("data")
    public DataDTO data;
    @JsonProperty("markupResponseData")
    public List<Object> markupResponseData = null;
    @JsonProperty("dataLayerEvent")
    public Object dataLayerEvent;
    @JsonProperty("dataLayers")
    public List<Object> dataLayers = null;

}
