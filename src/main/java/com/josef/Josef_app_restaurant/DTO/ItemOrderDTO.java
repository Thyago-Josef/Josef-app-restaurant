package com.josef.Josef_app_restaurant.DTO;

import java.time.LocalDateTime;

public abstract class ItemOrderDTO {

    private long id;
    private  String name;
    private double price;
    private String description;
    private LocalDateTime creationDateTime;

    public ItemOrderDTO(){

    }

    public ItemOrderDTO(long id,String name, double price, String description, LocalDateTime creationDateTime ){
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.creationDateTime = creationDateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
