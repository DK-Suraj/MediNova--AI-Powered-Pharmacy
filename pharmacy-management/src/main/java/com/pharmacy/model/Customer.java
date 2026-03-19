package com.pharmacy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message="Customer name is required")
    private String name;

    @Email(message="Enter a valid email")
    @NotBlank(message="Email is required")
    private String email;

    @NotBlank(message="Phone number is required")
    private String phone;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}