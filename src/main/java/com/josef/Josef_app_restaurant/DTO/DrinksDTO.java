package com.josef.Josef_app_restaurant.DTO;

import java.time.LocalDateTime;

public class DrinksDTO extends ItemOrderDTO{

    private Integer volume;
    private Boolean alcoholic;

    // ✅ CONSTRUTOR SEM ARGUMENTOS para JPA
    public DrinksDTO() {
        super();
        this.alcoholic = false;
    }


    public DrinksDTO(long id, String name, double price, String description, LocalDateTime dateCreation, Integer volume, Boolean alcoholic) {
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
