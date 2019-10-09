package com.bartindr.bartender.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bartindr.bartender.models.DrinkList;
import com.bartindr.bartender.models.User;
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
	public String checklist(@ModelAttribute("DrinkList")DrinkList drinkList, HttpSession session) {
		User user = (User) session.getAttribute("user");
		String newListName = user.getName() + "'s new list";
		drinkList.setName(newListName);
		session.setAttribute("currentDrinkList", drinkList);
		System.out.println(drinkList.getName());
		return "checklist.jsp";
	}
	
	@RequestMapping(value = "/ingredient/search", method = RequestMethod.GET)
	@ResponseBody
	public List<String> search(HttpServletRequest request){
		return mainService.searchIngredient(request.getParameter("term"));
	}
}
