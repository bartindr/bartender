package com.bartindr.bartender.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bartindr.bartender.services.MainService;

@RestController
public class ApiController {
	@Autowired
	private MainService mainService;
	
	@RequestMapping("/api/test")
	public void test() throws IOException{
		mainService.populateIngredientsDB();
	}
}
