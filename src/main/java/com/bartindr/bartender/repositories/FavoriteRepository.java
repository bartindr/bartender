package com.bartindr.bartender.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartindr.bartender.models.Favorite;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Long>{
	List<Favorite> findAll();
}
