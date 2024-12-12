package com.springboot.myfirstwebapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.myfirstwebapp.entity.User;
import com.springboot.myfirstwebapp.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {

	private final UserService userService;

	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String goToDefaultPage() {
		return "login";
	}

	@GetMapping("/login")
	public String goToLoginPage() {
		return "login";
	}

	@GetMapping("/welcome")
	public String goToWelcomePage(ModelMap model) {
		model.addAttribute("name", getLoggedInUser());
		return "welcome";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") User userDto, BindingResult result, Model model) {
		if (userService.userExists(userDto.getUsername())) {
			result.rejectValue("username", null, "username already exists. try different");
			return "register";
		}
		if (result.hasErrors()) {
			return "register";
		}
		userService.saveUser(userDto);
		return "redirect:/register?success";
	}

	public String getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
