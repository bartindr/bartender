package com.bartindr.bartender.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("/api/checklist/add")
	public void addIngredientToList(
			@RequestParam("ingredientName")String ingredientName,
			@RequestParam("drinkList")DrinkList drinkList,
			BindingResult result
			) {
		Ingredient ingredient = mainService.findIngredientByName(ingredientName);
		System.out.println(ingredient.getName());
		System.out.println(result);
		if(!result.hasErrors()) {
			DrinkListIngredient dLI = new DrinkListIngredient();
			dLI.setDrinkList(drinkList);
			dLI.setIngredient(ingredient);
			drinkList.setName("ANEM");
//			System.out.println(drinkList.getIngredients().get(0));
			System.out.println(dLI.getDrinkList().getName());
//			mainService.makeDrinkListIngredientRelationship(dLI);
		}
//		drinkList.getIngredients().add(ingredient);
//		System.out.println(drinkList.getIngredients().contains(ingredient));
	}
	
	@RequestMapping("/api/test2")
	public void test2() throws IOException {
	
		mainService.populateDrinksDB(mainService.allIngredients());
	}
	
}
