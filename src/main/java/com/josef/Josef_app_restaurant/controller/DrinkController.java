package com.josef.Josef_app_restaurant.controller;

import com.josef.Josef_app_restaurant.DTO.DrinksDTO;
import com.josef.Josef_app_restaurant.service.DrinksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drinks")
public class DrinkController {
    
    private final DrinksService drinkService;
    
    
    
    public DrinkController(DrinksService drinkService) {
        this.drinkService = drinkService;
    }
    
    
    @RequestMapping(value = "/all")
    public ResponseEntity<List<DrinksDTO>> findAllDrinks() {
        return ResponseEntity.ok(drinkService.findAllDrinks());
    }
    
    @RequestMapping(value = "/{id}")
    public ResponseEntity<Optional<DrinksDTO>> findDrinkById(Long id) {
        return ResponseEntity.ok(drinkService.findDrinkById(id));
    }
    @PostMapping(value = "/create")
    public ResponseEntity<DrinksDTO> createDrink(@RequestBody DrinksDTO drinksDTO) {
        return ResponseEntity.ok(drinkService.createDrink(drinksDTO));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<DrinksDTO> updateDrink(@RequestBody DrinksDTO drinksDTO) {
        return ResponseEntity.ok(drinkService.updateDrink(drinksDTO.getId(), drinksDTO));
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteDrink(@PathVariable Long id) {
        drinkService.deleteDrink(id);

    }
}
