package com.martiniriarte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MercadoSegundaManoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadoSegundaManoApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner initData(UserService userService, ProductService productService) {
//		return args -> {
//			
//			User user = new User("Luis Miguel", "López", null, "luismiguel.lopez@gmail.com", "1234");
//			user = userService.registrer(user);
//
//			User user2 = new User("Antonio", "García", null, "antonio.garcia@gmail.com", "1234");
//			user2 = userService.registrer(user2);
//
//			List<Product> listado = Arrays.asList(
//					new Product("Bicicleta de montaña", 100.0f,
//							"https://pictures.ssg-service.com/179/1799374_original_1.jpg", user),
//					new Product("Samsung Galaxy S10", 300.0f,
//							"https://bitwares.net/wp-content/uploads/2020/06/618FTkMHQ3L._AC_SL1500_-1.jpg",
//							user),
//					new Product("Raqueta de tenis", 10.5f,
//							"https://www.mistertennis.com/images/2018-media/Babolat-Boost-Aero-Racchetta-Tennis-121199_B.jpg",
//							user),
//					new Product("Xbox One X", 425.0f,
//							"https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Microsoft-Xbox-One-S-Console-wController-L.jpg/2560px-Microsoft-Xbox-One-S-Console-wController-L.jpg",
//							user2),
//					new Product("Trípode flexible", 10.0f,
//							"https://http2.mlstatic.com/D_NQ_NP_985884-MLA33005572475_112019-O.webp", user2),
//					new Product("Iphone 7 128 GB", 350.0f,
//							"https://store.storeimages.cdn-apple.com/4667/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ph/iphone7/rosegold/iphone7-rosegold-select-2016?wid=470&hei=556&fmt=jpeg&qlt=95&op_usm=0.5,0.5&.v=1472430205982",
//							user2));
//
//			listado.forEach(productService::insert);
//
//		};
//	}
}
