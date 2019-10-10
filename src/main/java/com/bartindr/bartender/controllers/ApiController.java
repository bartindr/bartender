package com.bartindr.bartender.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartindr.bartender.models.Drink;
import com.bartindr.bartender.models.DrinkList;
import com.bartindr.bartender.models.DrinkListIngredient;
import com.bartindr.bartender.models.Ingredient;
import com.bartindr.bartender.services.MainService;

@RestController
public class ApiController {
	@Autowired
	private MainService mainService;
	
	@RequestMapping("/api/test")
	public void test() throws IOException{
		mainService.populateIngredientsDB();
	}
//	
	@PostMapping("/api/checklist/add")
	public Ingredient addIngredientToList(
			@RequestParam("ingredientName")String ingredientName,
			@RequestParam("drinkListId")Long id,
//			BindingResult result, 
			HttpSession session
			) {
		
		DrinkList drinkList = mainService.findDrinkListByID(id);
		
		Ingredient ingredient = mainService.findIngredientByName(ingredientName);
	
		DrinkListIngredient dLI = new DrinkListIngredient();
		dLI.setDrinkList(drinkList);
		dLI.setIngredient(ingredient);
		System.out.println("added " + dLI.getIngredient().getName() + " to " + dLI.getDrinkList().getName());
		mainService.makeDrinkListIngredientRelationship(dLI);
			
		return ingredient;
	}
	
	@RequestMapping("/api/test2")
	public List<Drink> test2() throws IOException {
	
		return mainService.populateDrinksDB(mainService.allIngredients(), mainService.getAllDrinks());
	}
	
}
