package com.bartindr.bartender.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bartindr.bartender.models.User;
import com.bartindr.bartender.services.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String userAuth(@ModelAttribute("user")User user) {
		return "index.jsp";
	}
	
	// Make new user using bcrpyt
	@PostMapping("/user/register")
	public String registerUser(
			@Valid @ModelAttribute("user") User user,
			BindingResult result,
			HttpSession session
			) {
		if(result.hasErrors()) {
			return "index.jsp";
		} else {
			if(userService.getUserByEmail(user.getEmail()) != null) {
				return "redirect:/user/login/error";
			} else {
				System.out.println(user.getPassword());
				System.out.println("confirm: "+ user.getPasswordConfirmation());
				String pw1 = user.getPassword();
				String pw2 = user.getPassword();
				boolean checkPW = (pw1 == pw2);
				if(checkPW == false) {
					return "redirect:/user/register/errConfirm";
				}
				// SERVICE TO ADD USER TO DB w/ password hashing
				userService.createOrUpdateUser(user);
				session.setAttribute("user", user);
				return "redirect:/dashboard";
			}
		}
	}
	
	// REGISTRATION PASSWORD CONFIRMATION ERROR
	@GetMapping("/user/register/errConfirm")
	public String flashMessages(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorPw", "Passwords do not match!");
        return "redirect:/";
	}
	
	// LOGIN - EXISTING USER
	@PostMapping("/user/login")
	public String login(
			@RequestParam("email") String email, 
			@RequestParam("password") String password,
			HttpSession session
			) {
		if(userService.authenticateUser(email, password)) {
			User user = userService.getUserByEmail(email);
			session.setAttribute("user", user);
			return "redirect:/dashboard";
		} else {
			return "redirect:/user/login/error";
		}
		
	}
	
	// LOGIN ERROR
	@GetMapping("/user/login/error")
	public String flashMessageLogin(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorLogin", "Username and/or Password are invalid");
        return "redirect:/";
	}
	
}
