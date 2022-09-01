package com.roady.app.controllers;

import com.roady.app.entities.CustomUserDetails;
import com.roady.app.entities.User;
import com.roady.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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


// O T H E R  operations
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @GetMapping("/my_info")
    public String activeUserInfo(@AuthenticationPrincipal CustomUserDetails user, Model model){
        model.addAttribute("user", user);
        return "my_info";
    }
}