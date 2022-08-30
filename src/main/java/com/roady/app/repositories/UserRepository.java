package com.roady.app.repositories;

import com.roady.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    /* the email column is unique in the users table, so we define the findByEmail() method
     that returns a single User object based on email (no two users having the same email).*/
    @Query("SELECT u FROM User u where u.email=?1")
    User findUserByEmail(String email);
}

//JpaRepository is "bigger" than CRUD repository, has more methods inside
