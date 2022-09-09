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

    User currentUser;
//    currentUser = new User(1L, "jelena@jelena.lv", "1111", "Jelena", "Mikelsone", "97458394", 2.5, null, null );

    @Autowired
    private UserService userService;


// C R U D operations
    @PostMapping("/update_profile")
    public String saveUser( String email,  String password, String firstName, String lastName, String phone){
        User user = userService.getUserById(currentUser.getId());
        if(email!=null) user.setEmail(email);
        if(password!=null) user.setPassword(password);
        if(firstName!=null) user.setFirstName(firstName);
        if(lastName!=null) user.setLastName(lastName);
        if(phone!=null) user.setPhoneNumber(phone);

        currentUser=user;
        userService.saveUser(user);
        return "redirect:/user_form";
    }


    @GetMapping("/my_info")
    public String editUser(Model model){
        User user = currentUser;

        model.addAttribute("id", user.getId() );
        model.addAttribute("email", user.getEmail() );
        model.addAttribute("firstName", user.getFirstName() );
        model.addAttribute("lastName", user.getLastName() );
        model.addAttribute("phoneNumber", user.getPhoneNumber() );
        model.addAttribute("user", currentUser);
        return "my_info";
    }

    //works
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

    //works


    @GetMapping("/user_profile")
    public String showUserProfilePage(Model model){

        model.addAttribute("firstName", currentUser.getFirstName() );
        model.addAttribute("lastName", currentUser.getLastName() );


        return "user_profile";
    }


// O T H E R  operations

    //works
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());

        ArrayList<User> users =  userService.getAllUsers();
        this.activeUsersNumber = users.size();
        model.addAttribute("users", users);
        return "signup_form";
    }

    //works


    @GetMapping("/signup_form")
    public String showIndexPage(Model model){
        ArrayList<User> users =  userService.getAllUsers();
        this.activeUsersNumber = users.size();
        model.addAttribute("users", users);
        return "register";
    }

    @GetMapping("/login")
    public String activeUserInfo(
//            @RequestParam(name = "status", required = false) String status,
//            @RequestParam(name = "message", required = false) String message,
            Model model
    ){

//        model.addAttribute("status", status);
//        model.addAttribute("message", message);
        model.addAttribute("countUsers", this.activeUsersNumber );
        return "login";
    }

    @PostMapping("/login")
    public String successfulLogin(User user){

        try{
            User loggedInUser = userService.verifyUser(user.getEmail(), user.getPassword());
            this.currentUser=loggedInUser;
            return "redirect:user_profile/";
        }catch (Exception e){
            return "login"+e.getMessage();
        }
    }

    //works
    @GetMapping("/user_form")
    public String showUserFormPage(Model model){


        model.addAttribute("id", currentUser.getId() );
        model.addAttribute("email", currentUser.getEmail() );
        model.addAttribute("firstName", currentUser.getFirstName() );
        model.addAttribute("lastName", currentUser.getLastName() );
        model.addAttribute("phoneNumber", currentUser.getPhoneNumber() );
        model.addAttribute("avrRating", currentUser.getAvrRating() );
        model.addAttribute("registredAt", currentUser.getRegisteredAt() );
        model.addAttribute("countUsers", this.activeUsersNumber );
        return"user_form";
    }

    @GetMapping("/register_success")
    public String RegistrationWasSuccessful(){
        return "register_success";
    }

    @GetMapping("/logout")
    public String handleLogout(){
        currentUser=null;
        return "login";
    }


}