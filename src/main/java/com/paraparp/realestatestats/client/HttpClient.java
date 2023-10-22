package com.paraparp.realestatestats.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class HttpClient {

    private final RestTemplate restTemplate;

    public Object executeGet(String uri, HttpEntity<?> request, Class<?> clazz) {
        ResponseEntity<?> exchange = restTemplate.exchange(uri, HttpMethod.GET, request, clazz);
        return exchange.getBody();

    }
}
