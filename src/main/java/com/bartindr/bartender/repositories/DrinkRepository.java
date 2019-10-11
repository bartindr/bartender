package com.bartindr.bartender.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartindr.bartender.models.Drink;
import com.bartindr.bartender.models.Ingredient;

@Repository
public interface DrinkRepository extends CrudRepository<Drink, Long> {
	List<Drink> findAll();
	Optional<Drink> findByName(String name);
	Drink findByDrinkId(Long drinkId);
	
	List<Drink> findByIngredientsIn(List<Ingredient> ingredients);
	
	@Query(value="SELECT drinks.drink_id FROM drinks WHERE drinks.id IN (SELECT drinks.id FROM ingredients JOIN drinks_ingredients ON ingredients.id = drinks_ingredients.ingredient_id JOIN drinks ON drinks_ingredients.drink_id = drinks.id WHERE ingredients.id = ?1)", nativeQuery=true)
	List<Long> findDrinksByIngredientId(Long id);
	
}
