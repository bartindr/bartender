package com.bartindr.bartender.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bartindr.bartender.models.DrinkList;
import com.bartindr.bartender.models.DrinkListIngredient;
import com.bartindr.bartender.models.Ingredient;

public interface DrinkListIngredientRepository extends CrudRepository<DrinkListIngredient, Long>{
	List<DrinkListIngredient> findAll();
	
	DrinkListIngredient findByDrinkListAndIngredient(DrinkList drinkList, Ingredient ingredient);
}
