package com.josef.Josef_app_restaurant;

import com.josef.Josef_app_restaurant.entities.Drinks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JosefAppRestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(JosefAppRestaurantApplication.class, args);

		Drinks drink = new Drinks();

		drink.setPrice(2.5);
		System.out.println(drink.getPrice());

	}

}
