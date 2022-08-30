package com.roady.app.controllers;

import com.roady.app.entities.User;
import com.roady.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public void saveUser(User user){
        userService.saveUser(user);
    }

    public void showUserById(Long id, Model model){
        User user = userService.getById(id);
        model.addAttribute("user", user);
    }

    public void processRegistration(User user){
        userService.saveUser(user);
    }

    public void viewUsersList(Model model){
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);
    }

    public void showRegistrationForm(Model model){
        model.addAttribute("user", new User());
//        return "signup_form";
    }

    public String loggedinUser(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Object loggedinUserObject(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Object loggedInUserObject2(Authentication authentication){
        return authentication.getPrincipal();
    }

//    @GetMapping("/register")
//    public String showRegistrationForm(Model model){
//        model.addAttribute("user", new User());
//        return "signup_form";
//    }

//    @PostMapping("/process_register")
//    public String processRegistration(User user){
//        userService.saveUser(user);
//        return "register_success";
//    }

//    @GetMapping("/users")
//    public String viewUsersList(Model model){
//        List<User> listUsers = userService.listAll();
//        model.addAttribute("listUsers", listUsers);
//        return "users";
//    }

//    @GetMapping("/users/edit/{id}")
//    public String editUser(@PathVariable("id") Long id, Model model){
//        User user = userService.getById(id);
//        model.addAttribute("user", user);
//
//        return "user_form";
//    }

//    @PostMapping("/users/save")
//    public String saveUser(User user){
//        userService.saveUser(user);
//        return "redirect:/users";
//    }

}
