package com.roady.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @Autowired
    private UserController userController;

    @Autowired
    private CarController carController;

// HOME
    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

}