package com.roady.app.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ReviewPartAndUser extends User{

    private Long id;
    private UserReviews userReviews;

    public ReviewPartAndUser(String firstName, String lastname) {
        super();
    }

    public ReviewPartAndUser(Long id, String firstName, String lastname, UserReviews userReviews) {
        super();
        this.id = id;
        this.userReviews = userReviews;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public UserReviews getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(UserReviews userReviews) {
        this.userReviews = userReviews;
    }
}
