package com.pharmacy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message="Medicine name is required")
    private String name;

    @NotBlank(message="Category is required")
    private String category;

    @Positive(message="Price must be positive")
    private double price;

    @PositiveOrZero(message="Quantity must be zero or more")
    private int quantity;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}