package com.bartindr.bartender.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartindr.bartender.models.DrinkList;

@Repository
public interface DrinkListRepository extends CrudRepository<DrinkList, Long>{
	List<DrinkList> findAll();
}
