package com.martiniriarte;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.martiniriarte.model.Product;
import com.martiniriarte.model.User;
import com.martiniriarte.service.ProductService;
import com.martiniriarte.service.UserService;

@SpringBootApplication
public class MercadoSegundaManoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadoSegundaManoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(UserService userService, ProductService productService) {
		return args -> {

			User user = new User("Luis Miguel", "López Magaña", null, "luismi.lopez@openwebinars.net", "luismi");
			user = userService.registrer(user);

			User user2 = new User("Antonio", "García Martín", null, "antonio.garcia@openwebinars.net", "antonio");
			user2 = userService.registrer(user2);

			List<Product> listado = Arrays.asList(new Product("Bicicleta de montaña", 100.0f,
					"https://www.decathlon.es/media/835/8350582/big_23c25284-2810-415d-8bcc-e6bebdb536fc.jpg", user),
					new Product("Golf GTI Serie 2", 2500.0f,
							"https://www.minicar.es/large/Volkswagen-Golf-GTi-G60-Serie-II-%281990%29-Norev-1%3A18-i22889.jpg",
							user),
					new Product("Raqueta de tenis", 10.5f,
							"https://imgredirect.milanuncios.com/fg/2311/04/tenis/Raqueta-tenis-de-segunda-mano-en-Madrid-231104755_1.jpg?VersionId=T9dPhTf.3ZWiAFjnB7CvGKsvbdfPLHht",
							user),
					new Product("Xbox One X", 425.0f, "https://images.vibbo.com/635x476/860/86038583196.jpg", user2),
					new Product("Trípode flexible", 10.0f, "https://images.vibbo.com/635x476/860/86074256163.jpg",
							user2),
					new Product("Iphone 7 128 GB", 350.0f,
							"https://store.storeimages.cdn-apple.com/4667/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ph/iphone7/rosegold/iphone7-rosegold-select-2016?wid=470&hei=556&fmt=jpeg&qlt=95&op_usm=0.5,0.5&.v=1472430205982",
							user2));

			listado.forEach(productService::insert);

		};
	}
}
