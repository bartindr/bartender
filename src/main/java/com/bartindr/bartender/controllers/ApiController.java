package com.bartindr.bartender.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartindr.bartender.models.DrinkList;
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
			@RequestParam("drinkList")DrinkList drinkList
			) {
		Ingredient ingredient = mainService.findIngredientByName(ingredientName);
		drinkList.getIngredients().add(ingredient);
		System.out.println(drinkList.getIngredients().contains(ingredient));
	}
	
}
