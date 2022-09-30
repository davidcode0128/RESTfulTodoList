package com.Davidcode.RESTfulMyWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class ResTfulMyWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResTfulMyWebApplication.class, args);
	}	
}
