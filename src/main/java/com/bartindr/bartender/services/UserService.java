package com.bartindr.bartender.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartindr.bartender.models.User;
import com.bartindr.bartender.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// SERVICES //
	
	//Get ALL users
	public List<User> allUsers() {
		return userRepository.findAll();
	}
	
	//Get ONE user by id
	public User getUser(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			return null;
		}
	}
	
	//Get ONE user by email
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	// CREATE a new user
	public User createOrUpdateUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return userRepository.save(user);
	}
		
	// DELETE a user from the DB
	public User deleteUser(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			userRepository.delete(user);
			return user;
		} else {
			return null;
		}
	}
	
	/////////////////////
	// AUTHENTICATION //
	public boolean authenticateUser(String email, String password) {
		//find user by email
		User user = userRepository.findByEmail(email);
		// exit return false if null
		if(user == null) {
			return false;
		} else {
			//check password match
			if(BCrypt.checkpw(password, user.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	
	
}
