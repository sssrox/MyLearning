package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@ComponentScan("com.example.demo")
public class DemoPdf2xlsxApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoPdf2xlsxApplication.class, args);
	}

}

