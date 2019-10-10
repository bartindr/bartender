package com.bartindr.bartender.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/checklist/lists")
	public String userLists(
			@ModelAttribute("drinkList")DrinkList drinkList, 
			Model model, 
			HttpSession session
			) {
		User user = (User) session.getAttribute("user");

		try {
			model.addAttribute("drinkLists", user.getDrinkLists());
		}
		catch (Exception e) {
			ArrayList<DrinkList> tempList = new ArrayList<DrinkList>();
			DrinkList tempDL = new DrinkList();
			tempDL.setName("You currently have no drink lists. :(");
			tempList.add(tempDL);
			model.addAttribute("drinkLists", tempList);
		}
		

		System.out.println(user.getName());
		System.out.println(user.getDrinkLists());
		return "drinklists.jsp";
	}
	
	@PostMapping("/checklist/create")
	public String newListMake(
			@Valid @ModelAttribute("drinkList") DrinkList drinkList, 
			@RequestParam("userId") Long id,
			BindingResult result
			) {
		if(result.hasErrors()) {
			return "drinklists.jsp";
		} else {
			User user = userService.getUser(id);
			drinkList.setOwner(user);
			mainService.createOrUpdateDrinkList(drinkList);
			Long drinkListId = drinkList.getId();
			return "redirect:/checklist/" + drinkListId;
		}
	}
	
	@GetMapping("/checklist/{id}")
	public String checklist(
			@PathVariable("id") Long id,
			Model model,
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		DrinkList drinkList = mainService.findDrinkListByID(id);
		model.addAttribute("drinkList", drinkList);
		return "checklist.jsp";
	}
	
	@RequestMapping(value = "/ingredient/search", method = RequestMethod.GET)
	@ResponseBody
	public List<String> search(HttpServletRequest request){
		return mainService.searchIngredient(request.getParameter("term"));
	}
}
