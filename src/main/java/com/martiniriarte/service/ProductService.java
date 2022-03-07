package com.martiniriarte.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.martiniriarte.model.Buy;
import com.martiniriarte.model.Product;
import com.martiniriarte.model.User;
import com.martiniriarte.repository.ProductDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductDAO productDAO;
	private final StorageService storageService;

	public Product insert(Product product) {
		return productDAO.save(product);
	}

	public void delete(long id) {
		Product product = productDAO.findById(id).orElse(null);
		if (product != null) {
			storageService.delete(product.getUrlImage());
		}
		productDAO.deleteById(id);
	}

	public void delete(Product product) {
		System.out.println(product);
		if (!product.getUrlImage().isEmpty()) {
			storageService.delete(product.getUrlImage());
		}
		productDAO.delete(product);
	}

	public Product edit(Product product) {
		return productDAO.save(product);
	}

	public Product findById(long id) {
		return productDAO.findById(id).orElse(null);
	}

	public List<Product> findAll() {
		return productDAO.findAll();
	}

	public List<Product> ownerProducts(User user) {
		return productDAO.findByOwner(user);
	}

	public List<Product> purchaseProducts(Buy purchase) {
		return productDAO.findByPurchase(purchase);
	}

	public List<Product> unsoldProducts() {
		return productDAO.findByPurchaseIsNull();
	}

	public List<Product> find(String query) {
		return productDAO.findByNameContainsIgnoreCaseAndPurchaseIsNull(query);
	}

	public List<Product> findProductsByOwner(String query, User user) {
		return productDAO.findByNameContainsIgnoreCaseAndOwner(query, user);
	}

	public List<Product> findAllProducts(List<Long> ids) {
		return productDAO.findAllById(ids);
	}
}
