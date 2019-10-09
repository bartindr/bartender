package com.bartindr.bartender.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bartindr.bartender.models.Ingredient;
import com.bartindr.bartender.services.MainService;
import com.bartindr.bartender.services.UserService;

@Controller
public class MainController {
	@Autowired
	private MainService mainService;
	@Autowired
	private UserService userService;
	
	public MainController(
			MainService mainService,
			UserService userService) {
		this.mainService = mainService;
		this.userService = userService;
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		return "dashboard.jsp";
	}
	
	@GetMapping("/checklist")
	public String checklist(Model model) {
		List<Ingredient> ingredients = mainService.allIngredients();
		model.addAttribute("ingredients", ingredients);
		return "checklist.jsp";
	}
}
