package com.martiniriarte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martiniriarte.model.Buy;
import com.martiniriarte.model.Product;
import com.martiniriarte.model.User;

public interface ProductDAO extends JpaRepository<Product, Long> {

	List<Product> findByPurchase(Buy purchase);
	
	List<Product> findByPurchaseIsNull();
	
	List<Product> findByNameContainsIgnoreCaseAndPurchaseIsNull(String name);
	
	List<Product> findByNameContainsIgnoreCaseAndOwner(String name, User owner);
}
