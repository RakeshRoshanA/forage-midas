package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Balance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

// We rename the component and give it a unique name to avoid conflicts
@Component("balanceApiQuerier")
public class BalanceApiQuerier { 

    private static final Logger logger = LoggerFactory.getLogger(BalanceApiQuerier.class);
    private static final String BALANCE_API_URL = "http://localhost:33400/balance";
    
    private final RestTemplate restTemplate;

    @Autowired
    public BalanceApiQuerier(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Balance query(Long userId) {
        String url = UriComponentsBuilder.fromHttpUrl(BALANCE_API_URL)
            .queryParam("userId", userId)
            .toUriString();

        try {
            Balance response = restTemplate.getForObject(url, Balance.class);
            
            if (response != null) {
                return response;
            }
            
        } catch (Exception e) {
            logger.error("Failed to query balance for userId {}. Returning 0.0.", userId, e);
        }

        return new Balance(0.0f);
    }
}