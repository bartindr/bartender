package com.bartindr.bartender.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartindr.bartender.models.Drink;
import com.bartindr.bartender.models.DrinkIngredient;
import com.bartindr.bartender.models.DrinkList;
import com.bartindr.bartender.models.DrinkListIngredient;
import com.bartindr.bartender.models.Ingredient;
import com.bartindr.bartender.services.ChecklistService;
import com.bartindr.bartender.services.MainService;
import com.google.gson.JsonObject;

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
	
	@GetMapping("/api/drinks")
	public List<Drink> getAllDrinks(){
		return mainService.allDrinks();
	}
	
	@GetMapping("/api/drinkIngredientRelationships")
	public List<DrinkIngredient> getDIrelationshipsByIngredient(@RequestParam("ingredientName")String ingredientName){
		Ingredient ingredient = mainService.findIngredientByName(ingredientName);
		return mainService.drinksIngredientsByIngredient(ingredient);
	}
	
	@GetMapping("/api/drinkIngredientRelationshipsById")
	public List<DrinkIngredient> getDIrelationshipsByIngredient(@RequestParam("ingredientId")Long id){
		return mainService.drinksIngredientsByIngredientId(id);
	}
	
	@GetMapping("/api/drinkByIngredientId")
	public List<Long> getDrinkByIngredientId(@RequestParam("ingredientId")Long ingredientId){
		return mainService.drinksByIngredientId(ingredientId);
	}
	
	@GetMapping("/api/drinkId")
	public Drink findByDrinkId(@RequestParam("drinkId")Long drinkId) {
		return mainService.findByDrinkId(drinkId);
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
	
	@RequestMapping("api/test4")
	public String test4() throws IOException {
		return mainService.getDrinkJson((long) 11007);
	}
	
	@RequestMapping("api/test5")
	public Map<String, ArrayList<Object>> test5() throws IOException {
		return mainService.getDrinkJsonObject((long) 11007);
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
