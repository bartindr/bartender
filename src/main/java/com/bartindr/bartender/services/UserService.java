package com.bartindr.bartender.services;

import java.util.List;

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
	
	public List<User> allUsers() {
		return userRepository.findAll();
	}
	
}
