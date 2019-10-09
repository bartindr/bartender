package com.bartindr.bartender.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartindr.bartender.models.DrinkListIngredient;
import com.bartindr.bartender.models.Ingredient;
import com.bartindr.bartender.repositories.DrinkListIngredientRepository;
import com.bartindr.bartender.repositories.DrinkRepository;
import com.bartindr.bartender.repositories.IngredientRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class MainService {
	@Autowired
	private IngredientRepository ingredientRepository;
	@Autowired
	private DrinkRepository drinkRepository;
	@Autowired
	private DrinkListIngredientRepository drinkListRepository;
	
	
	public void populateIngredientsDB() throws IOException {
		URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/list.php?i=list");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
	    con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
	    
	    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    String inputLine;
	    StringBuffer content = new StringBuffer();
	    
	    while ((inputLine = in.readLine()) != null) {
	        content.append(inputLine);
	    }
	    
//	    System.out.println(content);
	    
	    Gson gson = new Gson();
	    Type type = new TypeToken<Map<String, Object>>(){}.getType();
	    Map<String, ArrayList<Object>> myMap = gson.fromJson(content.toString(), type);
	    ArrayList<Object> ingredients = myMap.get("drinks");
//	    System.out.println(ingredients);
	    
	    for( Object ingredient : ingredients) {
	    	String jobj = gson.toJsonTree(ingredient).getAsJsonObject().get("strIngredient1").toString();
//	    	System.out.println(jobj);
	    	Ingredient ing = new Ingredient(jobj);
	    	ingredientRepository.save(ing);
	    }
	    
	}
	
	// find all ingredients
	public List<Ingredient> allIngredients(){
		return ingredientRepository.findAll();
	}
	
	// find ingredient by name
	public Ingredient findIngredientByName(String name) {
		Optional<Ingredient> optionalIngredient = ingredientRepository.findByName(name);
		
		if(optionalIngredient.isPresent()) {
			return optionalIngredient.get();
		} else {
			return null;
		}
	}
	
	public List<String> searchIngredient(String keyword) {
		System.out.println("Typed");
		return ingredientRepository.search(keyword);
	}
	
	public DrinkListIngredient makeDrinkListIngredientRelationship(DrinkListIngredient drinkListIngredient) {
		return drinkListRepository.save(drinkListIngredient);
	}
	
}
