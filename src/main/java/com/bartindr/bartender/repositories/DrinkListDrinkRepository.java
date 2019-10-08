package com.bartindr.bartender.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartindr.bartender.models.DrinkListDrink;

@Repository
public interface DrinkListDrinkRepository extends CrudRepository<DrinkListDrink, Long>{
	List<DrinkListDrink> findAll();
}
