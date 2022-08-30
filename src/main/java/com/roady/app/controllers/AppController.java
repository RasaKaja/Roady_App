package com.roady.app.controllers;

import com.roady.app.entities.CustomUserDetails;
import com.roady.app.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserController userController;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        userController.showRegistrationForm(model);
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user){
        userController.processRegistration(user);
        return "register_success";
    }

//    @GetMapping("/users")
//    public String viewUsersList(Model model){
//        userController.viewUsersList(model);
//        return "users";
//    }

    @GetMapping("/profile")
    public String viewUserProfile(Model model){
        userController.viewUsersList(model);
        return "user_profile";
    }

    @GetMapping("/profile/edit")  //("/users/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model){
        userController.showUserById(id, model);
        return "user_form";
    }

//    @GetMapping("/my_info")  //("/users/edit/{id}")
//    public String userInfo(@PathVariable("id") Long id, Model model){
//        userController.showUserById(id, model);
//        return "my_info";
//    }

    @GetMapping("/my_info")
    public String activeUserInfo(@AuthenticationPrincipal CustomUserDetails user, Model model){
        model.addAttribute("user", user);
        return "my_info";
    }
//    @GetMapping("/profile/edit{id}")  //("/users/edit/{id}")
//    public String editUser(@PathVariable("id") Long id, Model model){
//        userController.showUserById(id, model);
//        return "user_profile";
//    }

    @PostMapping("/users/save")
    public String saveUser(User user){
        userController.saveUser(user);
        return "redirect:/profile";
    }


}
