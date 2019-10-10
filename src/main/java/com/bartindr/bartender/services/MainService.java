package com.bartindr.bartender.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartindr.bartender.models.Drink;
import com.bartindr.bartender.models.DrinkList;
import com.bartindr.bartender.models.DrinkListIngredient;
import com.bartindr.bartender.models.Ingredient;
import com.bartindr.bartender.repositories.DrinkListIngredientRepository;
import com.bartindr.bartender.repositories.DrinkListRepository;
import com.bartindr.bartender.repositories.DrinkRepository;
import com.bartindr.bartender.repositories.IngredientRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

@Service
public class MainService {
	@Autowired
	private IngredientRepository ingredientRepository;
	@Autowired
	private DrinkRepository drinkRepository;
 	@Autowired
 	private DrinkListIngredientRepository drinkListIngredientRepository;
 	@Autowired
 	private DrinkListRepository drinkListRepository;

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
	
	public List<Drink> populateDrinksDB(List<Ingredient> ingredients, List<Drink> drinks) throws IOException {
		for( Ingredient ingredient : ingredients) {
			URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/filter.php?i="+ingredient.getName().replace("\"", "").trim().replace(" ", "+"));
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
		    ArrayList<Object> bevs = myMap.get("drinks");

		    for( Object bev : bevs ) {
		    	JsonObject jobj = gson.toJsonTree(bev).getAsJsonObject();
		    	String name = jobj.get("strDrink").toString();
		    	Long drinkId = jobj.get("idDrink").getAsLong();
		    	String imgUrl = jobj.get("strDrinkThumb").toString();
		    	if(drinks.isEmpty()) {
		    		drinks.add(new Drink(name, drinkId, imgUrl));
		    	}
		    	for(int i = 0; i<drinks.size(); i++) {
		    		if(drinks.get(i).getDrinkId().equals(drinkId)) {
    					break;
    				}
    				if(i == drinks.size()-1) {
    					drinks.add(new Drink(name, drinkId, imgUrl));  
    				}
    			}
		    }
		    
		}
		drinkRepository.saveAll(drinks);
		return drinks;
	}
	
	public void addIngredientsToDrinks(List<Drink> drinks) throws IOException {
		for( Drink drink : drinks ) {
			URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=" + drink.getDrinkId());
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
		    ArrayList<Object> bevs = myMap.get("drinks");
		    
		    for( Object bev : bevs ) {
		    	JsonObject jobj = gson.toJsonTree(bev).getAsJsonObject();
		    	Map<String, String> ingredientMap = new HashMap<>();
		    	System.out.println(jobj.get("strIngredient4")==null);
		    	for(int i = 1; i < 15; i++) {
		    		if(jobj.get("strIngredient"+i)==null) {
		    			break;
		    		}
		    		ingredientMap.put(jobj.get("strIngredient"+i).toString(), jobj.get("strMeasure"+i).toString());		    		
		    	}
//		    	ingredientMap.put(jobj.get("strIngredient2").toString(), jobj.get("strMeasure2").toString());
//		    	ingredientMap.put(jobj.get("strIngredient3").toString(), jobj.get("strMeasure3").toString());
//		    	ingredientMap.put(jobj.get("strIngredient4").toString(), jobj.get("strMeasure4").toString());
//		    	ingredientMap.put(jobj.get("strIngredient5").toString(), jobj.get("strMeasure5").toString());
//		    	ingredientMap.put(jobj.get("strIngredient6").toString(), jobj.get("strMeasure6").toString());
//		    	ingredientMap.put(jobj.get("strIngredient7").toString(), jobj.get("strMeasure7").toString());
//		    	ingredientMap.put(jobj.get("strIngredient8").toString(), jobj.get("strMeasure8").toString());
//		    	ingredientMap.put(jobj.get("strIngredient9").toString(), jobj.get("strMeasure9").toString());
//		    	ingredientMap.put(jobj.get("strIngredient10").toString(), jobj.get("strMeasure10").toString());
//		    	ingredientMap.put(jobj.get("strIngredient11").toString(), jobj.get("strMeasure11").toString());
//		    	ingredientMap.put(jobj.get("strIngredient12").toString(), jobj.get("strMeasure12").toString());
//		    	ingredientMap.put(jobj.get("strIngredient13").toString(), jobj.get("strMeasure13").toString());
//		    	ingredientMap.put(jobj.get("strIngredient14").toString(), jobj.get("strMeasure14").toString());
//		    	ingredientMap.put(jobj.get("strIngredient15").toString(), jobj.get("strMeasure15").toString());
		    	System.out.println(ingredientMap);
		    }
		    
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
	
	public DrinkListIngredient makeDrinkListIngredientRelationship(DrinkListIngredient drinkListIngredient) {
		return drinkListIngredientRepository.save(drinkListIngredient);
	}
	
	public DrinkList createOrUpdateDrinkList(DrinkList drinkList) {
 		return drinkListRepository.save(drinkList);
	}
	
	public List<Drink> getAllDrinks() {
		return drinkRepository.findAll();
	}
		
	public DrinkList findDrinkListByID(Long id) {
		Optional<DrinkList> optionalDrinkList = drinkListRepository.findById(id);
		if(optionalDrinkList.isPresent()) {
			return optionalDrinkList.get();
		} else {
			return null;
		}
	}
	
	
}
