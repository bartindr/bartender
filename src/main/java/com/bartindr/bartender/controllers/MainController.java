package com.bartindr.bartender.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@GetMapping("/")
	public String renderLanding() {
		return "index.jsp";
	}
}
