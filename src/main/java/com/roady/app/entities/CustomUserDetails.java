package com.roady.app.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

    public String getPhoneNumber() {
        return user.getPhoneNumber();
    }

//    public Double getAvrRating() {
//        return user.getAvrRating();
//    }

    public Long getId() {
        return user.getId();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        return user.getFirstName();
    }
}