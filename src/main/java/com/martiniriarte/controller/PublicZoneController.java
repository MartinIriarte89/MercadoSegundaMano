package com.martiniriarte.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.martiniriarte.model.Product;
import com.martiniriarte.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicZoneController {

	private final ProductService productService;

	@ModelAttribute("productos")
	public List<Product> unsoldProducts() {
		return productService.unsoldProducts();
	}

	@GetMapping({ "/", "/index" })
	public String index(Model model, @RequestParam(name = "q", required = false) String query) {
		if (query != null) {
			model.addAttribute("productos", productService.find(query));
		}
		return "index";
	}
	
	@GetMapping("/producto/{id}")
	public String showProduct(Model model, @PathVariable Long id) {
		Product product = productService.findById(id); 
		if (product != null) {
			model.addAttribute("producto", product);
			return "producto";
		}
		return "redirect:/public";
	}

}
