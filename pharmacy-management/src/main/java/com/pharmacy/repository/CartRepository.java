package com.pharmacy.repository;

import com.pharmacy.model.Cart;
import com.pharmacy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByUser(User user);
}