package com.martiniriarte.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.martiniriarte.model.Buy;
import com.martiniriarte.model.Product;
import com.martiniriarte.model.User;
import com.martiniriarte.repository.BuyDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuyService {

	private final BuyDAO buyDAO;
	private final ProductService productService;

	public Buy insert(Buy purchase, User user) {
		purchase.setOwner(user);
		return buyDAO.save(purchase);
	}

	public Buy insert(Buy purchase) {
		return buyDAO.save(purchase);
	}

	public Product addProductToPurchase(Product product, Buy purchase) {
		product.setPurchase(purchase);
		return productService.edit(product);
	}

	public Buy findById(long id) {
		return buyDAO.findById(id).orElse(null);
	}

	public List<Buy> findAll() {
		return buyDAO.findAll();
	}

	public List<Buy> findByOwner(User user) {
		return buyDAO.findByOwner(user);
	}

}
