package com.josef.Josef_app_restaurant.repository;


import com.josef.Josef_app_restaurant.entities.Drinks;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinksRepository extends ItensOrderRepository<Drinks> {

//    List<Drinks> findByType(String type);

    List<Drinks> findByAlcoholic(boolean alcoholic);


    List<Drinks> findByPriceBetween(Double minPrice, Double maxPrice);

//    List<Drinks> findByActiveTrue();
}
