package com.josef.Josef_app_restaurant.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
@Entity
public abstract class ItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private  String name;
    private double price;
    private String description;
    private LocalDateTime creationDateTime;


    public ItemOrder(long id, String name, double price, String description, LocalDateTime creationDateTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.creationDateTime = creationDateTime;
    }

    public ItemOrder() {

    }

    public long getId() {
        return id;
    }

    public void setId(long itemOrder) {
        this.id = itemOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }
}
