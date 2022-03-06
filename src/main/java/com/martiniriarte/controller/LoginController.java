package com.martiniriarte.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.martiniriarte.model.User;
import com.martiniriarte.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final UserService userService;
	
	@GetMapping("/")
	public String welcome() {
		return "redirect:/public/";
	}
	
	@GetMapping("/auth/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping("/auth/register")
	public String register(@ModelAttribute User user) {
		userService.registrer(user);
		return "redirect:/auth/login";
	}
}
