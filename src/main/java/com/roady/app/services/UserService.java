package com.roady.app.services;

import com.roady.app.entities.User;
import com.roady.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

// C R U D operations
    public void saveUser(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

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

// O T H E R operations
    public boolean checkIfIsUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return true;
        }
        return false;
    }

}