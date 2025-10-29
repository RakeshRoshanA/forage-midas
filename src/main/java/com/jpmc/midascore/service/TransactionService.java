package com.jpmc.midascore.service;

import com.jpmc.midascore.component.IncentiveApi;
import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRecordRepository;
import com.jpmc.midascore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRecordRepository transactionRecordRepository;

    @Autowired
    private IncentiveApi incentiveApi;

    @Transactional 
    public void processTransaction(Transaction transaction) {
        
        UserRecord sender = userRepository.findById(transaction.getSenderId());
        UserRecord recipient = userRepository.findById(transaction.getRecipientId());

        // 1. Validate sender and recipient exist
        if (sender == null || recipient == null) {
            logger.warn("Transaction failed: Sender or recipient not found. Discarding.");
            return; 
        }

        // 2. Validate the sender's balance
        if (sender.getBalance() < transaction.getAmount()) {
            logger.warn("Transaction failed: Insufficient funds for sender {}. Discarding.", sender.getName());
            return; 
        }

        // --- All validations passed. Process the transaction. ---

        try {
            // 3. Get Incentive Amount
            double incentiveAmount = incentiveApi.getIncentiveAmount(transaction);
            
            // 4. Update balances
            
            // Deduct the base transaction amount from the sender
            // We use (float) to cast the result back to float before setting it.
            sender.setBalance((float) (sender.getBalance() - transaction.getAmount()));
            
            // Credit the recipient with the base amount PLUS the incentive
            // We use (float) to cast the result back to float before setting it.
            recipient.setBalance((float) (recipient.getBalance() + transaction.getAmount() + incentiveAmount));

            // 5. Save the updated user balances
            userRepository.save(sender);
            userRepository.save(recipient);

            // 6. Create and save the new TransactionRecord
            TransactionRecord record = new TransactionRecord(sender, recipient, transaction.getAmount(), incentiveAmount);
            transactionRecordRepository.save(record);

            logger.info("Transaction processed successfully for sender {}. Incentive: {}", sender.getName(), incentiveAmount);

        } catch (Exception e) {
            logger.error("Error during transaction processing. Rolling back.", e);
            throw e; 
        }
    }
}