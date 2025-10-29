package com.jpmc.midascore.controller;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Balance;
import com.jpmc.midascore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // Marks this class as a REST endpoint handler
public class BalanceController {

    private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * Exposes a GET endpoint at /balance to retrieve a user's current balance.
     *
     * @param userId The ID of the user whose balance is requested.
     * @return A Balance object serialized to JSON.
     */
    @GetMapping("/balance")
    public Balance getBalance(@RequestParam("userId") long userId) {
        logger.info("Received request for balance check for userId: {}", userId);

        // 1. Find the user by ID (using the method defined in UserRepository)
        UserRecord user = userRepository.findById(userId);
        
        // 2. Check if the user exists
        if (user == null) {
            logger.warn("User not found for ID: {}. Returning balance of 0.", userId);
            // If the user doesn't exist, return a Balance of 0.0
            return new Balance(0.0f);
        }

        // 3. User exists, return their current balance.
        logger.info("Found user {} with balance: {}", user.getName(), user.getBalance());
        
        // Note: Balance class constructor takes a float argument.
        return new Balance(user.getBalance());
    }
}