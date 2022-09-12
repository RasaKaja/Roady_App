package com.roady.app;

import com.roady.app.controllers.UserController;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class AppApplication {
	public static void main(String[] args) {

		SpringApplication.run(AppApplication.class, args);
		UserController userController = new UserController();
		System.out.println(userController.currentUser);
	}
}