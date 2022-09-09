package com.roady.app.repositories;

import com.roady.app.entities.DriverReview;
import com.roady.app.entities.PassengerReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerReviewRepository extends JpaRepository<PassengerReview, Long> {

    public Iterable<PassengerReview> findPassengerReviewById(Long id);
}
