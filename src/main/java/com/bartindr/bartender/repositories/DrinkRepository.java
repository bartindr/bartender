package com.bartindr.bartender.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartindr.bartender.models.Drink;

@Repository
public interface DrinkRepository extends CrudRepository<Drink, Long> {
	List<Drink> findAll();
	Optional<Drink> findByName(String name);
}
