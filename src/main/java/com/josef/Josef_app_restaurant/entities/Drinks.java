package com.josef.Josef_app_restaurant.entities;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class Drinks extends ItemOrder {

    private Integer volume;
    private Boolean alcoholic;

    // ✅ CONSTRUTOR SEM ARGUMENTOS para JPA
    public Drinks() {
        super();
        this.alcoholic = false;
    }


    public Drinks(long id, String name, double price, String description, LocalDateTime dateCreation, Integer volume, Boolean alcoholic) {
        super(id, name, price, description, dateCreation);
        this.volume = volume;
        this.alcoholic = alcoholic;
    }


    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Boolean getAlcoholic() {
        return alcoholic;
    }

    public void setAlcoholic(Boolean alcoholic) {
        this.alcoholic = alcoholic;
    }


}
