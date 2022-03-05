package com.martiniriarte.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotEmpty
	private String name;
	
	@Min(0)
	private String price;
	
	private String urlImage;
	
	@ManyToOne
	private User owner;
	
	@ManyToOne
	private Buy purchase;

	public Product(String name, String price, String urlImage, User owner) {
		super();
		this.name = name;
		this.price = price;
		this.urlImage = urlImage;
		this.owner = owner;
	}	
}
