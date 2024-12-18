package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "CRUD Operations Trail",
		description = "All API definitions ....",
		version = "1.0.0"))
public class SpringdatajpaexApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringdatajpaexApplication.class, args);
	}

}
