package com.paraparp.realestatestats.services;

public enum IdealistaLocation {
    RIAZOR_ROSALES("a-coruna/riazor-los-rosales", "57.84598237584738,+23.051198959350607", "24.409244860154534,+-39.922433853149414"),
    CIUDAD_VIEJA("a-coruna/ciudad-vieja-centro", "58.502772676210604,+13.732917785644556", "23.257310155975194,+-30.52001190185546"),
    TOTAL_CORUNA("a-coruna-a-coruna", "57.84598237584738,+23.051198959350607", "24.409244860154534,+-39.922433853149414");

    public static final String PATH = "https://www.idealista.com/ajax/listingcontroller/listingmapajaxgrouped.ajax?";
    private final String locationUri;
    private final String northEast;
    private final String southWest;

    IdealistaLocation(String locationUri, String northEast, String southWest) {
        this.locationUri = locationUri;
        this.northEast = northEast;
        this.southWest = southWest;
    }

    public String buildUrl() {
        return PATH + "locationUri=" + locationUri + "&typology=1&operation=1&zoom=5&northEast=" + northEast + "&southWest=" + southWest;
    }
}