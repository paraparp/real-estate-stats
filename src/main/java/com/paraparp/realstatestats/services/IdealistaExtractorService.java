package com.paraparp.realstatestats.services;

import com.paraparp.realstatestats.client.HttpClient;
import com.paraparp.realstatestats.model.entity.MainObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class IdealistaExtractorService {

    private final HttpClient httpClient;

    public MainObject getIdealistaStats(String uri){

        return (MainObject) this.httpClient.executeGet(uri, getEntity(), MainObject.class);
    }

    private  HttpEntity getEntity() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("accept-language", "es-ES,es;q=0.9,gl;q=0.8,en;q=0.7,de;q=0.6");
        headers.set("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36");
        headers.set("x-requested-with", "XMLHttpRequest");
        headers.set("Cookie", "datadome=2eoL2~xM_VE1RWe0gYvxNxrniYrU_UlKEC3w63hzVz5rRLvrxEmAYAeATbKmsTVV~mP~t3kiTE3SxfK9PwMzLSDXcT1XdsJAqEZ6J2C--5tfGP2uwMLi0G~VRKsNc08e; SESSION=eea26d055b230b2a~24c518cf-6e72-4d84-a601-3cd03d4d4d0f; userUUID=d23475a5-97a6-417b-b5a5-a5e3d50acc1c");

        HttpEntity request = new HttpEntity(headers);

        return request;
    }

}
