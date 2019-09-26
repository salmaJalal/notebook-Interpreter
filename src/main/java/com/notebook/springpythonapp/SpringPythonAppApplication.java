package com.notebook.springpythonapp;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ComponentScan("com.notebook")
public class SpringPythonAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPythonAppApplication.class, args);
	}

}
