package com.jpmc.midascore.repository;

import com.jpmc.midascore.entity.TransactionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Tells Spring this is a Repository bean
public interface TransactionRecordRepository extends JpaRepository<TransactionRecord, Long> {
    // Spring Data JPA automatically creates all the necessary methods
    // like .save(), .findById(), .findAll() based on this interface.
    // We don't need to write any code here.
}