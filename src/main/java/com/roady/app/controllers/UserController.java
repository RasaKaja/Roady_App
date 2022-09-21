package com.roady.app.controllers;

import com.roady.app.entities.User;
import com.roady.app.repositories.UserRepository;
import com.roady.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    UserRepository userRepository;

    int activeUsersNumber;
    Integer ridesNumber;

    public User currentUser;


    @Autowired
    private UserService userService;



// C R U D operations
    @PostMapping("/update_profile")
    public String saveUser(String email, String firstName, String lastName, String phone){
        if(this.currentUser==null){
            return "login";
        }else{
        User user = currentUser;
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phone);
            currentUser=user;
            userService.saveUser(user);
            return "redirect:/user_form";}

    }

    @GetMapping("/my_info")
    public String editUser(Model model){
        if(this.currentUser==null){
            return "login";
        }else{
        User user = currentUser;
        model.addAttribute("email", user.getEmail() );
        model.addAttribute("firstName", user.getFirstName() );
        model.addAttribute("lastName", user.getLastName() );
        model.addAttribute("phoneNumber", user.getPhoneNumber() );
        model.addAttribute("user", currentUser);
            model.addAttribute("users", this.activeUsersNumber);
            model.addAttribute("rides", this.ridesNumber);
        return "my_info";}
    }

    //works
    @PostMapping("/process_register")
    public String processRegistration(User user){
        userService.saveUser(user);
        return "register_success";
    }

    @GetMapping("/users")
    public String viewUsersList(Model model){
        if(this.currentUser==null){
            return "login";
        }else{
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);
            model.addAttribute("users", this.activeUsersNumber);
            model.addAttribute("rides", this.ridesNumber);
            model.addAttribute("firstName", this.currentUser.getFirstName());
        return "users";}
    }


    @GetMapping("/user_profile")
    public String showUserProfilePage(Model model){
        if(this.currentUser==null){
            return "login";
        }else{
        model.addAttribute("firstName", currentUser.getFirstName() );
        model.addAttribute("lastName", currentUser.getLastName() );
            model.addAttribute("users", this.activeUsersNumber);
            model.addAttribute("rides", this.ridesNumber);
        return "user_profile";}
    }


// O T H E R  operations

    //works
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
            model.addAttribute("user", new User());
            model.addAttribute("countUsers", this.activeUsersNumber );
        model.addAttribute("users", this.activeUsersNumber);
        model.addAttribute("rides", this.ridesNumber);
            return "signup_form";
    }

    @GetMapping("/signup_form")
    public String showIndexPage(Model model){
        ArrayList<User> users =  userService.getAllUsers();
        this.activeUsersNumber = users.size();
        model.addAttribute("users", users);
        model.addAttribute("rides", this.ridesNumber);
        return "register";
    }



    @PostMapping("/login")
    public String successfulLogin(User user){
        try{
            User loggedInUser = userService.verifyUser(user.getEmail(), user.getPassword());
            this.currentUser=loggedInUser;
            return "redirect:user_profile";
        }catch (Exception e){
            return "redirect:login?status=user_not_found";
        }
    }

    //works


    @GetMapping("/register_success")
    public String RegistrationWasSuccessful(Model model){
        model.addAttribute("users", this.activeUsersNumber);
        model.addAttribute("rides", this.ridesNumber);
        return "register_success";
    }

    @GetMapping("/logout")
    public String handleLogout(Model model){
        currentUser=null;
        model.addAttribute("users", this.activeUsersNumber);
        model.addAttribute("rides", this.ridesNumber);
        return "redirect:login";
    }

    @GetMapping("/password_change")
    public String handlePasswordChangeRequest(
            @RequestParam(name="status", required = false) String status,
            Model model
    ){
        if(this.currentUser==null){
            return "login";
        }else{
        model.addAttribute("status", status);
            model.addAttribute("users", this.activeUsersNumber);
            model.addAttribute("rides", this.ridesNumber);
            model.addAttribute("firstName", this.currentUser.getFirstName());
        return "password_change";}
    }

    @PostMapping("/change_password")
    public String handlePasswordChangeRequest(String password, String newPassword){
        User user = userService.getUserById(currentUser.getId());
        if(password.equals(currentUser.getPassword())){
            user.setPassword(newPassword);
            currentUser=user;
            userService.saveUser(user);
            return "redirect:user_form";
        }
        else{
            return"redirect:password_change?status=incorrect_password";
        }
    }

}