package com.pharmacy.repository;

import com.pharmacy.model.Sale;
import com.pharmacy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {

    // 1. Find all orders for a specific logged-in User
    List<Sale> findByUser(User user);

    // 2. Find orders by User Email (if needed for specific lookups)
    List<Sale> findByUserEmail(String email);

    // 3. Admin Search: Find sales where customer name contains the keyword (Case Insensitive)
    // IMPORTANT: Make sure your Sale model has a field named 'customerName'
    List<Sale> findByCustomerNameContainingIgnoreCase(String customerName);

    // 4. Admin Search: Find sales by Medicine Name (Optional but very useful)
    List<Sale> findByMedicineNameContainingIgnoreCase(String medicineName);
}