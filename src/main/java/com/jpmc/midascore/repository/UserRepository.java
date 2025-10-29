package com.jpmc.midascore.repository;

import com.jpmc.midascore.entity.UserRecord;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserRecord, Long> {

    UserRecord findById(long id);

    // This is the new method we are adding.
    // Spring Data JPA will automatically understand this method
    // and write the correct SQL (e.g., "SELECT * FROM user_record WHERE name = ?")
    UserRecord findByName(String name);
}