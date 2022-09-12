package com.roady.app.controllers;

import com.roady.app.entities.User;
import com.roady.app.repositories.UserRepository;
import com.roady.app.services.UserService;
import net.bytebuddy.pool.TypePool;
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

    public User currentUser;
//    currentUser = new User(1L, "jelena@jelena.lv", "1111", "Jelena", "Mikelsone", "97458394", 2.5, null, null );

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
        return "users";}
    }

    //works


    @GetMapping("/user_profile")
    public String showUserProfilePage(Model model){
        if(this.currentUser==null){
            return "login";
        }else{

        model.addAttribute("firstName", currentUser.getFirstName() );
        model.addAttribute("lastName", currentUser.getLastName() );


        return "user_profile";}
    }


// O T H E R  operations

    //works
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
            model.addAttribute("user", new User());
            model.addAttribute("countUsers", this.activeUsersNumber );
            return "signup_form";
    }

    @GetMapping("/signup_form")
    public String showIndexPage(Model model){

        ArrayList<User> users =  userService.getAllUsers();
        this.activeUsersNumber = users.size();
        model.addAttribute("users", users);
        return "register";
    }

    @GetMapping("/login")
    public String activeUserInfo(
            @RequestParam(name="status", required = false) String status,
            Model model
    ){

        model.addAttribute("status", status);
        model.addAttribute("countUsers", this.activeUsersNumber );

        ArrayList<User> users =  userService.getAllUsers();
        this.activeUsersNumber = users.size();
        model.addAttribute("users", activeUsersNumber);

        return "login";
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
    @GetMapping("/user_form")
    public String showUserFormPage(Model model){
        if(this.currentUser==null){
            return "login";
        }else{
        model.addAttribute("email", currentUser.getEmail() );
        model.addAttribute("firstName", currentUser.getFirstName() );
        model.addAttribute("lastName", currentUser.getLastName() );
        model.addAttribute("phoneNumber", currentUser.getPhoneNumber() );
        model.addAttribute("avrRating", currentUser.getAvrRating() );
        model.addAttribute("registredAt", currentUser.getRegisteredAt() );
        model.addAttribute("countUsers", this.activeUsersNumber );
        return"user_form";
        }
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

    @GetMapping("/password_change")
    public String handlePasswordChangeRequest(
            @RequestParam(name="status", required = false) String status,
            Model model
    ){
        if(this.currentUser==null){
            return "login";
        }else{
        model.addAttribute("status", status);
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