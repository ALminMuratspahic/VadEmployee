package com.VaadinEmployees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.vaadin.flow.spring.annotation.EnableVaadin;

@SpringBootApplication

public class VaadinEmployeesApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaadinEmployeesApplication.class, args);
		
	}

}
