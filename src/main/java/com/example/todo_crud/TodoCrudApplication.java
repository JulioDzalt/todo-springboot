package com.example.todo_crud;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoCrudApplication {
	private static Logger log = LogManager.getLogger(TodoCrudApplication.class);

	public static void main(String[] args) {
		System.out.println("Hola");
		log.info("Iniciando app");
		SpringApplication.run(TodoCrudApplication.class, args);
	}

}
