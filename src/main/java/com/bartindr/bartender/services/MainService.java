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

import com.bartindr.bartender.models.Drink;
import com.bartindr.bartender.models.Ingredient;
import com.bartindr.bartender.repositories.DrinkRepository;
import com.bartindr.bartender.repositories.IngredientRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class MainService {
	@Autowired
	private IngredientRepository ingredientRepository;
	@Autowired
	private DrinkRepository drinkRepo;
	
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
	    
	    Gson gson = new Gson();
	    Type type = new TypeToken<Map<String, Object>>(){}.getType();
	    Map<String, ArrayList<Object>> myMap = gson.fromJson(content.toString(), type);
	    ArrayList<Object> ingredients = myMap.get("drinks");
	    
	    for( Object ingredient : ingredients) {
	    	String jobj = gson.toJsonTree(ingredient).getAsJsonObject().get("strIngredient1").toString();
	    	Ingredient ing = new Ingredient(jobj);
	    	if(!this.checkExistingIngredient(jobj)) {
	    		ingredientRepository.save(ing);	    		
	    	}
	    }    
	}
	
	public void populateDrinksDB(List<Ingredient> ingredients, List<Drink> drinks) throws IOException {
		
		for( Ingredient ingredient : ingredients) {
			URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/filter.php?i="+ingredient.getName().replace("\"", "").replace(" ", "+"));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			System.out.println(content);
			
		}
	}
	
	//Check db to see if there are any duplicate ingredients. (For future when db gets updated)
	public Boolean checkExistingIngredient(String name) {
		Optional<Ingredient> i = ingredientRepository.findByName(name);
		
		if(i.isPresent()) {
			return true;
		} else {
			return false;
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
	
	
}
