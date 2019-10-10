package com.bartindr.bartender.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartindr.bartender.models.Drink;
import com.bartindr.bartender.models.DrinkList;
import com.bartindr.bartender.models.DrinkListIngredient;
import com.bartindr.bartender.models.Ingredient;
import com.bartindr.bartender.services.ChecklistService;
import com.bartindr.bartender.services.MainService;

@RestController
public class ApiController {
	@Autowired
	private MainService mainService;
	@Autowired
	private ChecklistService cLService;
	
	@RequestMapping("/api/test")
	public void test() throws IOException{
		mainService.populateIngredientsDB();
	}
//	
	@PostMapping("/api/checklist/add")
	public DrinkListIngredient addIngredientToList(
			@RequestParam("ingredientName")String ingredientName,
			@RequestParam("drinkListId")Long id,
//			BindingResult result, 
			HttpSession session
			) {
		
		DrinkList drinkList = mainService.findDrinkListByID(id);
		
		Ingredient ingredient = mainService.findIngredientByName(ingredientName);
		
		if(drinkList.getIngredients().contains(ingredient)) {
			return null;
		}
	
		DrinkListIngredient dLI = new DrinkListIngredient();
		dLI.setDrinkList(drinkList);
		dLI.setIngredient(ingredient);
		System.out.println("added " + dLI.getIngredient().getName() + " to " + dLI.getDrinkList().getName());
		mainService.makeDrinkListIngredientRelationship(dLI);
		
		System.out.println(dLI.getDrinkList().getName());
		System.out.println(dLI.getIngredient().getName());
		System.out.println(dLI.getId());
		return dLI;
	}
	
	@RequestMapping("/api/test2")
	public List<Drink> test2() throws IOException {
	
		return mainService.populateDrinksDB(mainService.allIngredients(), mainService.getAllDrinks());
	}
	
	@RequestMapping("/api/test3")
	public void test3() throws IOException {
		mainService.addIngredientsToDrinks(mainService.getAllDrinks());
	}
	
	@DeleteMapping("/checklist/api/{id}/delete/{ingId}")
	public Long deleteIngredient(
			@PathVariable(value="id")Long id, 
			@PathVariable("ingId")Long ingId,
			@RequestParam("drinkListId")Long drinkListId,
			@RequestParam("ingredientId")Long ingredientId
			) {
		System.out.println("IN DELETE");
		DrinkList drinkList = mainService.findDrinkListByID(drinkListId);
		Ingredient ingredient = cLService.findIngredientById(ingredientId);
		DrinkListIngredient dLI = cLService.findDrinkListIngredientRelationship(drinkList, ingredient);
		cLService.removeDrinkListIngredientRelationship(dLI);
		return ingredientId;
	}
	
	
}
