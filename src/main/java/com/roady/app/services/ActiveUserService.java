package com.roady.app.services;

import com.roady.app.entities.ActiveUser;
import com.roady.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class ActiveUserService {

    UserRepository userRepository;

    @Autowired
    public ActiveUserService(UserRepository userRepository) {this.userRepository=userRepository;}


//    public ActiveUser setLoggedInUser(String email) throws Exception {
//        ActiveUser activeUser = new ActiveUser();
//               activeUser.setActiveUser(userRepository.findByEmail(email));
//        if (activeUser == null) {
//            throw new Exception("User not found.");
//        }
//        return new ActiveUser(activeUser.getActiveUser());
//    }
}
