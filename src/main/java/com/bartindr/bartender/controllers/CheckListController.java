package com.bartindr.bartender.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bartindr.bartender.services.ChecklistService;
import com.bartindr.bartender.services.MainService;

@Controller
public class CheckListController {
	@Autowired
	private MainService mainService;
	@Autowired
	private ChecklistService cLService;
	
	@GetMapping("/drinks/{id}")
	public String displayDrink (@PathVariable("id") Long id, Model model) throws IOException {
		model.addAttribute("json", mainService.getDrinkJsonObject(id));
		model.addAttribute("drink",mainService.findByDrinkId(id));
		return "showDrink.jsp";
	}
}
