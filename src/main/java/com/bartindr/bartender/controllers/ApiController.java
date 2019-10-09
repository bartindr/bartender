package com.bartindr.bartender.controllers;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
	
//	@RequestMapping("/api/test")
//	public void test() throws IOException{
//		mainService.populateIngredientsDB();
//	}
//	
	@PostMapping("/api/checklist/add")
	public Ingredient addIngredientToList(
			@RequestParam("ingredientName")String ingredientName,
//			BindingResult result, 
			HttpSession session
			) {
		
		DrinkList drinkList = (DrinkList) session.getAttribute("currentDrinkList");
		
		Ingredient ingredient = mainService.findIngredientByName(ingredientName);
		System.out.println("add " + ingredient.getName() + " to " + drinkList.getName());
	
			DrinkListIngredient dLI = new DrinkListIngredient();
			dLI.setDrinkList(drinkList);
			dLI.setIngredient(ingredient);
//			mainService.makeDrinkListIngredientRelationship(dLI);
			System.out.println(dLI.getDrinkList().getName());
			System.out.println(drinkList.getIngredients());
			System.out.println(dLI.getDrinkList().getIngredients());
			System.out.println(dLI.getIngredient().getName());
			System.out.println(ingredient.getDrinkLists());
			
//		drinkList.getIngredients().add(ingredient);
//		System.out.println(drinkList.getIngredients().contains(ingredient));
		return ingredient;
	}
	
	@RequestMapping("/api/test2")
	public void test2() throws IOException {
	
		mainService.populateDrinksDB(mainService.allIngredients());
	}
	
}
