package com.example.ApiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApplication {
	
	public static void main(String[] args) {
		SpringApplication.run (ApiGatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes ()
					 .route (p -> p
										  .path ("/api/v1/**")
							              .uri("lb://Authorization/"))

					 
					 .route (p -> p
										  .path ("/api/v2/user/**","/api/v2/register")
							              .uri("lb://task-service/"))

					 .route (p -> p
										  .path ("/api/v3/**")
				                          .uri("lb://reminder-service/"))


					 
					 .build ();
	}
}