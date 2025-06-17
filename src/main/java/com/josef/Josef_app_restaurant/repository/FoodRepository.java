package com.josef.Josef_app_restaurant.repository;

import com.josef.Josef_app_restaurant.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends ItensOrderRepository<Food> {


    List<Food> findByCategory(String category);

    List<Food> findByVegetarian(boolean vegetarian);

    List<Food> findByVegan(boolean vegan);

    List<Food> findByPriceBetween(Double minPrice, Double maxPrice);


    List<Food> findByActiveTrue();

    List<Food> findBySpiceLevel(String spiceLevel);

    List<Food> findByPreparationTimeLessThanEqual(Integer maxPreparationTime);

    List<Food> findByIngredientsContaining(String ingredient);

    List<Food> findByAllergensNotContaining(String allergen);

    List<Food> findMostPopular(int limit);
}
