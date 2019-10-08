package com.bartindr.bartender.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartindr.bartender.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	// method for lookup of all users
	List<User> findAll();
	//method to save or update optional user
	void save(Optional<User> optionalUser);
	//method to find by user email
	User findByEmail(String email);
}
