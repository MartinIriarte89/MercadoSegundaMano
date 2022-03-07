package com.martiniriarte.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.martiniriarte.model.Buy;
import com.martiniriarte.model.Product;
import com.martiniriarte.model.User;
import com.martiniriarte.service.BuyService;
import com.martiniriarte.service.ProductService;
import com.martiniriarte.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/app")
@RequiredArgsConstructor
public class BuyController {

	private final BuyService buyService;
	private final ProductService productService;
	private final UserService userService;
	private final HttpSession session;
	private User user;

	@ModelAttribute("carrito")
	public List<Product> productsInCart() {
		@SuppressWarnings("unchecked")
		List<Long> contents = (List<Long>) session.getAttribute("carrito");
		return (contents == null) ? null : productService.findAllProducts(contents);
	}

	@ModelAttribute("total_carrito")
	public Double totalCashCart() {
		List<Product> productosCarrito = productsInCart();

		if (productosCarrito != null) {
			return productosCarrito.stream().mapToDouble(p -> p.getPrice()).sum();
		}

		return 0.0;
	}

	@ModelAttribute("mis_compras")
	public List<Buy> getPurchase() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		user = userService.findByEmail(email);
		return buyService.findByOwner(user);
	}

	@GetMapping("/carrito")
	public String getBuyCart(Model model) {
		return "app/compra/carrito";
	}

	@GetMapping("/carrito/add/{id}")
	public String addBuyCart(Model model, @PathVariable Long id) {
		@SuppressWarnings("unchecked")
		List<Long> contenido = (List<Long>) session.getAttribute("carrito");
		if (contenido == null) {
			contenido = new ArrayList<>();
		}

		if (!contenido.contains(id)) {
			contenido.add(id);
		}

		session.setAttribute("carrito", contenido);
		return "redirect:/app/carrito";
	}

	@GetMapping("/carrito/eliminar/{id}")
	public String deleteBuyCart(Model model, @PathVariable Long id) {
		@SuppressWarnings("unchecked")
		List<Long> contents = (List<Long>) session.getAttribute("carrito");
		if (contents == null) {
			return "redirect:/public";
		}

		contents.remove(id);

		if (contents.isEmpty()) {
			session.removeAttribute("carrito");
		}

		else {
			session.setAttribute("carrito", contents);
		}

		return "redirect:/app/carrito";
	}

	@GetMapping("/carrito/finalizar")
	public String checkout() {
		@SuppressWarnings("unchecked")
		List<Long> contents = (List<Long>) session.getAttribute("carrito");
		if (contents == null) {
			return "redirect:/public";
		}

		List<Product> products = productsInCart();

		Buy buy = buyService.insert(new Buy(), user);

		products.forEach(product -> buyService.addProductToPurchase(product, buy));
		session.removeAttribute("carrito");

		return "redirect:/app/compra/factura/" + buy.getId();

	}

	@GetMapping("/miscompras")
	public String checkPurchases(Model model) {
		return "/app/compra/listado";
	}

	@GetMapping("/compra/factura/{id}")
	public String ticket(Model model, @PathVariable Long id) {
		Buy buy = buyService.findById(id);
		List<Product> products = productService.purchaseProducts(buy);
		model.addAttribute("productos", products);
		model.addAttribute("compra", buy);
		model.addAttribute("total_compra", products.stream().mapToDouble(product -> product.getPrice()).sum());
		return "/app/compra/factura";
	}

}
