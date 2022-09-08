package com.roady.app.controllers;

import com.roady.app.entities.User;
import com.roady.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


// C R U D operations
    @PostMapping("/users/save")
    public String saveUser(User user){
        userService.saveUser(user);
        return "redirect:/profile";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user){
        userService.saveUser(user);
        return "register_success";
    }

    @GetMapping("/users")
    public String viewUsersList(Model model){
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/profile/edit")
    public String editUser(@PathVariable("id") Long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user_form";
    }

    @GetMapping("/user_profile")
    public String showUserProfilePage(Model model){

        return "user_profile";
    }


// O T H E R  operations
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());

        ArrayList<User> users =  userService.getAllUsers();

        model.addAttribute("users", users);
        return "signup_form";
    }

    //should be changed
    @GetMapping("/my_info")
    public String activeUserInfo(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        return "my_info";
    }

    @GetMapping("/signup_form")
    public String showIndexPage(Model model){
        ArrayList<User> users =  userService.getAllUsers();

        model.addAttribute("users", users);
        return "register";
    }

    @GetMapping("/login")
    public String activeUserInfo(){

        return "login";
    }

//    @PostMapping("/login")
//    public String successfulLogin(User user){
//        try{
//            User loggedInUser = userService.verifyUser(user);
//            return "redirect:my_info";
//        }catch (Exception e){
//            return "login";
//        }
//    }


}