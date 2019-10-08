package com.bartindr.bartender.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartindr.bartender.models.Ingredient;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long>{
	List<Ingredient> findAll();
}