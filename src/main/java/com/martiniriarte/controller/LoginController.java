package com.martiniriarte.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.martiniriarte.model.User;
import com.martiniriarte.service.StorageService;
import com.martiniriarte.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final UserService userService;
	private final StorageService storageService;

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
	public String register(@ModelAttribute User user, @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			String imagen = storageService.store(file);
			user.setUrlAvatar(MvcUriComponentsBuilder.fromMethodName(FilesController.class, "serveFile", imagen).build()
					.toUriString());

		}
		userService.registrer(user);
		return "redirect:/auth/login";
	}
}
