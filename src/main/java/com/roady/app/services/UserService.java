package com.roady.app.services;

import com.roady.app.entities.User;
import com.roady.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


// C R U D operations
    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).get();
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public ArrayList<User> getAllUsers(){
        return (ArrayList<User>) userRepository.findAll();
    }

    public User verifyUser(User userLoginRequest) throws Exception{

        User foundUser = this.userRepository.findByEmailAndPassword(userLoginRequest.getEmail(), userLoginRequest.getPassword());
        if(foundUser==null) {throw new Exception("Username or password is not correct");}

        return foundUser;
    }


}