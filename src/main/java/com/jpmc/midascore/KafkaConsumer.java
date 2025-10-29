package com.jpmc.midascore;

import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.service.TransactionService; // Import the new service
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; // Import Autowired
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    // Inject the TransactionService
    @Autowired
    private TransactionService transactionService;

    /**
     * This method listens for messages and passes them to the TransactionService for processing.
     *
     * @param transaction The Transaction object deserialized from the Kafka message.
     */
    @KafkaListener(topics = "${general.kafka-topic}", groupId = "midas-consumers-new")
    public void listen(Transaction transaction) {
        
        // No more logging here. We just pass it to the service.
        try {
            transactionService.processTransaction(transaction);
        } catch (Exception e) {
            // Log if the service reports an error (like insufficient funds)
            logger.error("Failed to process transaction: {}", e.getMessage());
        }
    }
}