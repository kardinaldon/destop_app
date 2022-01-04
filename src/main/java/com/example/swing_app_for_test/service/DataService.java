package com.example.swing_app_for_test.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class DataService {

    private RestTemplate restTemplate;

    public ResponseEntity<String> getDataFromURL(String apiHost){
        restTemplate = new RestTemplate();
        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(apiHost, HttpMethod.GET, null, String.class);

        } catch (HttpStatusCodeException ex) {
            response = new ResponseEntity<String>(ex.getResponseBodyAsString()
                    , ex.getResponseHeaders(), ex.getStatusCode());
        }
        return response;
    }

}
