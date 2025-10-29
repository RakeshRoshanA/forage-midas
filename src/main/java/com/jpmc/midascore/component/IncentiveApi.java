package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component 
public class IncentiveApi {

    private static final Logger logger = LoggerFactory.getLogger(IncentiveApi.class);
    private static final String INCENTIVE_API_URL = "http://localhost:8080/incentive";

    // Now, Spring knows where to find the RestTemplate from RestTemplateConfig
    private final RestTemplate restTemplate; 

    @Autowired
    public IncentiveApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    // THE @Bean METHOD HAS BEEN REMOVED

    public double getIncentiveAmount(Transaction transaction) {
        logger.info("Requesting incentive for transaction...");
        
        try {
            ResponseEntity<Incentive> response = restTemplate.postForEntity(
                INCENTIVE_API_URL, 
                transaction, 
                Incentive.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                double incentiveAmount = response.getBody().getAmount();
                logger.info("Successfully received incentive: {}", incentiveAmount);
                return incentiveAmount;
            }

        } catch (Exception e) {
            logger.error("Failed to connect to Incentive API or receive valid response. Defaulting to 0.0.", e);
        }
        
        return 0.0;
    }
}