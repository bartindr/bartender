package com.bartindr.bartender.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bartindr.bartender.models.Ingredient;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long>{
	List<Ingredient> findAll();
	Optional<Ingredient> findByName(String name);
	
	@Query("SELECT name FROM Ingredient where name like %:keyword%")
	public List<String> search(@Param("keyword")String keyword);
}
