package com.josef.Josef_app_restaurant.controller;


import com.josef.Josef_app_restaurant.DTO.FoodDTO;
import com.josef.Josef_app_restaurant.service.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;


    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }


    @RequestMapping(value = "/all")
    public ResponseEntity<List<FoodDTO>> findAllFood() {
        return ResponseEntity.ok(foodService.findAllFood());
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Optional<FoodDTO>> findFoodById(Long id) {
        return ResponseEntity.ok(foodService.findFoodById(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<FoodDTO> createFood(@RequestBody FoodDTO foodDTO) {
        return ResponseEntity.ok(foodService.createFood(foodDTO));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<FoodDTO> updateFood(@RequestBody FoodDTO foodDTO) {
        return ResponseEntity.ok(foodService.updateFood(foodDTO.getId(), foodDTO));
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
    }


}
