package com.roady.app.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveUser {

    private User currentUser;

    User user = new User(1L, "jelena@jelena.lv", "1111", "Jelena", "Mikelsone", "97458394", 2.5, null, null );

    public void changeCurrentUser(User user){
        this.currentUser=this.user;
    }

    public User currentActiveUser(){
        return this.user;//change to currentActiveUSer
    }

}
