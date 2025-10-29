package com.jpmc.midascore;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration // Tells Spring this class contains bean definitions
public class RestTemplateConfig {

    // This method creates and manages the RestTemplate bean
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Return a configured RestTemplate instance
        return builder.build();
    }
}