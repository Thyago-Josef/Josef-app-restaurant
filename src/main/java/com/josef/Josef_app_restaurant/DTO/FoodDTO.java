package com.josef.Josef_app_restaurant.DTO;

import java.time.LocalDateTime;

public class FoodDTO extends ItemOrderDTO{
    private float weight;

    private int quantity;


    // ✅ CONSTRUTOR SEM ARGUMENTOS para JPA
    public FoodDTO() {
        super();
    }


    public FoodDTO(long id, String name, double price, String descricao, LocalDateTime dataCriacao, float weight, int quantity) {
        super(id, name, price, descricao, dataCriacao);
        this.weight = weight;
        this.quantity = quantity;
    }


    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
