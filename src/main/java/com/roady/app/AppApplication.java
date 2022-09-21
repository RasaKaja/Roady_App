package com.roady.app;

import com.roady.app.controllers.UserController;
import com.roady.app.entities.User;
import com.roady.app.repositories.UserRepository;
import com.roady.app.services.UserService;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;


@SpringBootApplication

public class AppApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);

	}
}