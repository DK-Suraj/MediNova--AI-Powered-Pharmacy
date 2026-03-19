package com.pharmacy.repository;

import com.pharmacy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    // Already exists
    User findByEmailAndPassword(String email, String password);

    // Add this method
    User findByEmail(String email);
}