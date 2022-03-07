package com.martiniriarte.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.martiniriarte.model.Product;
import com.martiniriarte.model.User;
import com.martiniriarte.service.ProductService;
import com.martiniriarte.service.StorageService;
import com.martiniriarte.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/app")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	private final UserService userService;
	private final StorageService storageService;
	private User user;

	@ModelAttribute("misproductos")
	public List<Product> misProductos() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		user = userService.findByEmail(email);
		return productService.ownerProducts(user);
	}

	@GetMapping("/misproductos")
	public String list(Model model, @RequestParam(name = "q", required = false) String query) {
		if (query != null)
			model.addAttribute("misproductos", productService.findProductsByOwner(query, user));

		return "app/producto/lista";
	}

	@GetMapping("/misproductos/{id}/eliminar")
	public String eliminar(@PathVariable Long id) {
		Product product = productService.findById(id);
		if (product.getPurchase() == null)
			productService.delete(product);
		return "redirect:/app/misproductos";
	}

	@GetMapping("/producto/nuevo")
	public String nuevoProductoForm(Model model) {
		model.addAttribute("producto", new Product());
		return "app/producto/form";
	}

	// Arreglar validacion
	@PostMapping("/producto/nuevo/submit")
	public String nuevoProductoSubmit(@ModelAttribute @Valid Product producto, Errors errors,
			@RequestParam("file") MultipartFile file, Model model) {

		if (errors.hasErrors()) {
			model.addAttribute("producto", producto);
			return "app/producto/form";
		}

		if (!file.isEmpty()) {
			String imagen = storageService.store(file);
			producto.setUrlImage(MvcUriComponentsBuilder.fromMethodName(FilesController.class, "serveFile", imagen)
					.build().toUriString());
		} else {
			producto.setUrlImage("");
		}
		producto.setOwner(user);
		productService.insert(producto);
		return "redirect:/app/misproductos";
	}

}
