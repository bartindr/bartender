package com.bartindr.bartender.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bartindr.bartender.models.DrinkListIngredient;

public interface DrinkListIngredientRepository extends CrudRepository<DrinkListIngredient, Long>{
	List<DrinkListIngredient> findAll();
}
