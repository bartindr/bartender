package com.bartindr.bartender.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartindr.bartender.models.DrinkList;
import com.bartindr.bartender.models.DrinkListIngredient;
import com.bartindr.bartender.models.Ingredient;
import com.bartindr.bartender.repositories.DrinkListDrinkRepository;
import com.bartindr.bartender.repositories.DrinkListIngredientRepository;
import com.bartindr.bartender.repositories.DrinkListRepository;
import com.bartindr.bartender.repositories.IngredientRepository;

@Service
public class ChecklistService {
	@Autowired
	private DrinkListRepository drinkListRepo;
	@Autowired
	private DrinkListIngredientRepository drinkListIngredientRepo;
	@Autowired
	private DrinkListDrinkRepository drinkListDrinkRepo;
	@Autowired
	private IngredientRepository ingredientRepo;
	
	public DrinkListIngredient findDrinkListIngredientRelationship(
			DrinkList drinkList, 
			Ingredient ingredient
			) {
		return drinkListIngredientRepo.findByDrinkListAndIngredient(drinkList, ingredient);
	}
	
	public DrinkListIngredient removeDrinkListIngredientRelationship(DrinkListIngredient drinkListIngredient) {
		DrinkListIngredient toRemove = drinkListIngredient;
		drinkListIngredientRepo.delete(drinkListIngredient);
		return toRemove;
	}
	
	public Ingredient findIngredientById(Long ingredientId) {
		Optional<Ingredient> optionalIngredient = ingredientRepo.findById(ingredientId);
		if(optionalIngredient.isPresent()) {
			return optionalIngredient.get();
		} else {
			return null;
		}
	}
	
}
