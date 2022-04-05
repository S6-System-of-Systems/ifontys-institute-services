package com.appliedscience.api.domain.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ConsumingRest8vance {
    @Value("${8vance.endpoint}")
    private String Uri;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public ResponseEntity<?> get(RestTemplate restTemplate) {
        
    }


}
