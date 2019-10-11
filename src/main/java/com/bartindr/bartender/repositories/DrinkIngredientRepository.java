package com.bartindr.bartender.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartindr.bartender.models.DrinkIngredient;
import com.bartindr.bartender.models.Ingredient;

@Repository
public interface DrinkIngredientRepository extends CrudRepository<DrinkIngredient, Long> {
	List<DrinkIngredient> findAll();
	List<DrinkIngredient> findByIngredient(Ingredient ingredient);
	
	@Query(value="SELECT * FROM drinks_ingredients WHERE ingredient_id = ?1", nativeQuery = true)
	List<DrinkIngredient> findDrinkIngredientNative(Long ingredientId);
	@Query(value="SELECT * FROM drinks WHERE drinks.id IN (SELECT drinks.id FROM ingredients JOIN drinks_ingredients ON ingredients.id = drinks_ingredients.ingredient_id JOIN drinks ON drinks_ingredients.drink_id = drinks.id WHERE ingredients.id = ?1)", nativeQuery=true)
	List<Object> findDrinksByIngredientId(Long id);
}
