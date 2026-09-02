package com.api_gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class ApiGatewayConfiguration {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				
				.route(p -> p
						.path("/addProductToCategory/**")
						.uri("lb://inventory-service"))
				
				.route(p -> p
						.path("/cartProducts/**")
						.uri("lb://inventory-service"))
				
				.route(p -> p
						.path("/removeFromStock/**")
						.uri("lb://inventory-service"))
				
				.route(p -> p
						.path("/addToStock/**")
						.uri("lb://inventory-service"))
				
				.route(p -> p
						.path("/createProduct/**")
						.uri("lb://inventory-service"))
				
				.route(p -> p
						.path("/getProduct/**")
						.uri("lb://inventory-service"))
				
				.route(p -> p
						.path("/deleteProduct/**")
						.uri("lb://inventory-service"))
				
				.route(p -> p
						.path("/products")
						.uri("lb://inventory-service"))
				
				.route(p -> p
						.path("/addProductToCart/**")
						.uri("lb://shop-service"))
				
				.route(p -> p
						.path("/addQuantity/**")
						.uri("lb://shop-service"))
				
				.route(p -> p
						.path("/deleteProductInCart/**")
						.uri("lb://shop-service"))
				
				.route(p -> p
						.path("/cart/**")
						.uri("lb://shop-service"))
				
				/*.route(p -> p
						.path("/createProduct/**")
						.uri("lb://shop-service"))
				
				.route(p -> p
						.path("/getProduct/**")
						.uri("lb://shop-service"))
				
				.route(p -> p
						.path("/deleteProduct/**")
						.uri("lb://shop-service"))
						*/
				
				.route(p -> p
				        .path("/getStock/**")
				        .uri("lb://inventory-service"))

				.route(p -> p
				        .path("/products")
				        .uri("lb://inventory-service"))

				.route(p -> p
				        .path("/updateProduct/**")
				        .uri("lb://inventory-service"))
				
				.route(p -> p
				        .path("/createCategory/**")
				        .uri("lb://inventory-service"))

				.route(p -> p
				        .path("/updateCategory/**")
				        .uri("lb://inventory-service"))

				.route(p -> p
				        .path("/removeProductFromCategory/**")
				        .uri("lb://inventory-service"))

				.route(p -> p
				        .path("/categories/**")
				        .uri("lb://inventory-service"))
				
				.route(p -> p
						.path("/createOrder/**")
						.uri("lb://shop-service"))
				
				.route(p -> p
						.path("/deleteOrder/**")
						.uri("lb://shop-service"))
				
				.route(p -> p
						.path("/getAllOrdersforUser/**")
						.uri("lb://shop-service"))
				
				.route(p -> p
						.path("/addProductToOrder/**")
						.uri("lb://shop-service"))
				
				.route(p -> p
						.path("/payOrder/**")
						.uri("lb://shop-service"))
				
				.route(p -> p
						.path("/refundOrder/**")
						.uri("lb://shop-service"))
				
				.route(p -> p
						.path("/deposit/**")
						.uri("lb://wallet-service"))
				
				.route(p -> p
						.path("/withdrawal/**")
						.uri("lb://wallet-service"))
				
				.route(p -> p
						.path("/history/**")
						.uri("lb://wallet-service"))
				
				.route(p -> p
						.path("/users/**")
						.uri("lb://wallet-service"))
				
				.route(p -> p
						.path("/login/**")
						.uri("lb://wallet-service"))
				
				.route(p -> p
						.path("/wallet/**", "/wallets/**")
						.uri("lb://wallet-service"))
				
				.route(p -> p
						.path("/createWallet/**")
						.uri("lb://wallet-service"))
				
				.build();
				
	}

}
