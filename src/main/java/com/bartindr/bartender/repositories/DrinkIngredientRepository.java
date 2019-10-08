package com.bartindr.bartender.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartindr.bartender.models.DrinkIngredient;

@Repository
public interface DrinkIngredientRepository extends CrudRepository<DrinkIngredient, Long> {
	List<DrinkIngredient> findAll();
}
