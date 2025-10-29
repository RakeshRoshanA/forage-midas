package com.jpmc.midascore.foundation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents the response object from the external Incentive API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Incentive {
    
    private double amount;

    // Default constructor required for JSON deserialization
    public Incentive() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}