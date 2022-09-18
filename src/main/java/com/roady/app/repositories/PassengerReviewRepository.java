package com.roady.app.repositories;

import com.roady.app.entities.DriverReview;
import com.roady.app.entities.PassengerReview;
import com.roady.app.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface PassengerReviewRepository extends JpaRepository<PassengerReview, Long> {

    public Iterable<PassengerReview> findPassengerReviewById(Long id);

    @Query(value = "SELECT AVG(review) FROM passenger_review WHERE passenger_id=?1", nativeQuery = true)
    Double avgRating(Long userId);

    PassengerReview findByRide(Ride ride);


}