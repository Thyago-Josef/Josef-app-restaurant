package com.josef.Josef_app_restaurant.mapper;

import com.josef.Josef_app_restaurant.DTO.DrinksDTO;
import com.josef.Josef_app_restaurant.DTO.FoodDTO;
import com.josef.Josef_app_restaurant.entities.Drinks;
import com.josef.Josef_app_restaurant.entities.Food;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemOrderMapper {

    FoodDTO toFoodDTO(Food food);

    DrinksDTO toDrinksDTO(Drinks drinks);

    Food toFoodEntity(FoodDTO foodDTO);

    Drinks toDrinksEntity(DrinksDTO drinksDTO);






}
