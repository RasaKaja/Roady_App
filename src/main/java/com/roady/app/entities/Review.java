package com.roady.app.entities;

public interface Review {

    double getReview();
    Long getId();
    void setId(Long id);

    Long getUserId();
    void setUserId(Long userId);

    void setReview(double review);
}
